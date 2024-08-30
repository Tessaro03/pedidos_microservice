package com.pedido.service;

import java.util.List;
import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import com.pedido.dtos.PagamentoInputDTO;
import com.pedido.dtos.pedidoProduto.input.PedidoSolicitacaoInputDTO;
import com.pedido.infra.security.TokenService;
import com.pedido.infra.security.UsuarioDTO;
import com.pedido.model.Pedido;
import com.pedido.model.Produto;
import com.pedido.model.Status;
import com.pedido.repository.PedidoRepository;
import com.pedido.validations.validadorPedido.ValidadorPedidos;


public class PedidoServiceTest {

    @InjectMocks
    private PedidoService service;

    @Mock
    private PedidoRepository repository;

    @Mock
    private TokenService tokenService;

    @Mock
    private RabbitTemplate rabbitTemplate;

    @Mock
    private ValidadorPedidos validador;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Usuário do tipo cliente acessando apenas pedidos com seu id")
    void testVerPedidosCase1() {
        UsuarioDTO dto = new UsuarioDTO(1l, "teste", "teste@gmail.com", "Cliente");

        when(tokenService.extrairInformacoes(any())).thenReturn(dto);
        service.verPedidos(any());

        verify(repository, times(1)).findAllByIdCliente(any(Long.class));
    }

    @Test
    @DisplayName("Usuário do tipo loja acessando apenas pedidos com seu id")
    void testVerPedidosCase2() {
        UsuarioDTO dto = new UsuarioDTO(1l, "teste", "teste@gmail.com", "Loja");

        when(tokenService.extrairInformacoes(any())).thenReturn(dto);
        service.verPedidos(any());

        verify(repository, times(1)).findAllByIdLoja(any( Long.class));
    }

    @Test
    void testConfirmarPedido() {
        UsuarioDTO dto = new UsuarioDTO(1l, "teste", "teste@gmail.com", "Cliente");
        when(tokenService.extrairInformacoes(any())).thenReturn(dto);
        
        Pedido pedido = new Pedido(1L, 1L, 1L, 10d, LocalDateTime.now(), Status.PENDENTE, any());
        when(repository.getReferenceById(1l)).thenReturn(pedido);
        
        service.confirmarPedido(1l, any());
    
        assertEquals(pedido.getStatus(), Status.CONFIRMADO);
        verify(repository, times(1)).save(pedido);
        verify(rabbitTemplate, times(1)).convertAndSend(eq("pedido.concluido"), any(PagamentoInputDTO.class));
    }

    @Test
    @DisplayName("Pedido com status SEPARADO, sendo enviado apenas para 1 queue")
    void testCancelarPedidoCase1() {
        UsuarioDTO dto = new UsuarioDTO(1l, "teste", "teste@gmail.com", "Cliente");
        when(tokenService.extrairInformacoes(any())).thenReturn(dto);

        List<Produto> produtos = List.of(new Produto(1l, 1l,1l, "teste", 10d, 100d, null, 10, any()));
        Pedido pedido = new Pedido(1L, 1L, 1L, 10d, LocalDateTime.now(), Status.SEPARADO, produtos);

        when(repository.getReferenceById(1l)).thenReturn(pedido);

        service.cancelarPedido(1l, any());

        verify(rabbitTemplate, times(1)).convertAndSend(eq("pedido.cancelado-produto"), any(PedidoSolicitacaoInputDTO.class));
        verify(rabbitTemplate, times(0)).convertAndSend(eq("pedido.cancelado-pagamento"), any(Long.class));

        assertEquals(pedido.getStatus(), Status.CANCELADO);
        verify(repository, times(1)).save(pedido);
    }

    @Test
    @DisplayName("Pedido com status CONFIRMADO, sendo enviado para 2 queue")
    void testCancelarPedidoCase2() {
        UsuarioDTO dto = new UsuarioDTO(1l, "teste", "teste@gmail.com", "Cliente");
        when(tokenService.extrairInformacoes(any())).thenReturn(dto);

        List<Produto> produtos = List.of(new Produto(1l, 1l,1l, "teste", 10d, 100d, null, 10, any()));
        Pedido pedido = new Pedido(1L, 1L, 1L, 10d, LocalDateTime.now(), Status.CONFIRMADO, produtos);

        when(repository.getReferenceById(1l)).thenReturn(pedido);

        service.cancelarPedido(1l, any());

        verify(rabbitTemplate, times(1)).convertAndSend(eq("pedido.cancelado-produto"), any(PedidoSolicitacaoInputDTO.class));
        verify(rabbitTemplate, times(1)).convertAndSend(eq("pedido.cancelado-pagamento"), any(Long.class));

        assertEquals(pedido.getStatus(), Status.CANCELADO);
        verify(repository, times(1)).save(pedido);
    }

    @Test
    @DisplayName("Pedido com status PENDENTE, sendo enviado para nenhuma queue")
    void testCancelarPedidoCase3() {
        UsuarioDTO dto = new UsuarioDTO(1l, "teste", "teste@gmail.com", "Cliente");
        when(tokenService.extrairInformacoes(any())).thenReturn(dto);

        List<Produto> produtos = List.of(new Produto(1l, 1l,1l, "teste", 10d, 100d, null, 10, any()));
        Pedido pedido = new Pedido(1L, 1L, 1L, 10d, LocalDateTime.now(), Status.PENDENTE, produtos);

        when(repository.getReferenceById(1l)).thenReturn(pedido);

        service.cancelarPedido(1l, any());

        verify(rabbitTemplate, times(0)).convertAndSend(eq("pedido.cancelado-produto"), any(PedidoSolicitacaoInputDTO.class));
        verify(rabbitTemplate, times(0)).convertAndSend(eq("pedido.cancelado-pagamento"), any(Long.class));

        assertEquals(pedido.getStatus(), Status.CANCELADO);
        verify(repository, times(1)).save(pedido);
    }

     


}
