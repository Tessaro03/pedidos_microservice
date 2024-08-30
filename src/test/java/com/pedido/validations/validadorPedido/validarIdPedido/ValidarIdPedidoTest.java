package com.pedido.validations.validadorPedido.validarIdPedido;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.pedido.infra.exceptions.ValidacaoException;
import com.pedido.model.Pedido;
import com.pedido.repository.PedidoRepository;

public class ValidarIdPedidoTest {

    @InjectMocks
    private ValidarIdPedido validacao;

    @Mock
    private PedidoRepository repository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Id de pedido existe")
    void testValidarIdCase1() {
        when(repository.existsById(any())).thenReturn(true);
        assertDoesNotThrow( () -> validacao.validarId(any()));
    }

    @Test
    @DisplayName("Id de pedido não existe")
    void testValidarIdCase2() {
        when(repository.existsById(any())).thenReturn(false);
        assertThrows( ValidacaoException.class,() -> validacao.validarId(any()));
    }

	@Test
    @DisplayName("Pedido pertence ao usuário")
	void testValidarSePedidoEDeUsuarioCase1() {
		Pedido pedido = new Pedido();
        pedido.setIdCliente(1l);

        when(repository.getReferenceById(any())).thenReturn(pedido);

        assertDoesNotThrow( () -> validacao.validarSePedidoEDeUsuario(any(),1l));
	}

    @Test
    @DisplayName("Pedido não pertence ao usuário")
	void testValidarSePedidoEDeUsuarioCase2() {
		Pedido pedido = new Pedido();
        pedido.setIdCliente(1l);

        when(repository.getReferenceById(any())).thenReturn(pedido);

        assertThrows( ValidacaoException.class, () -> validacao.validarSePedidoEDeUsuario(any(),2l));
	}



}
