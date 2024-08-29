package com.pedido.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pedido.dtos.PagamentoInputDTO;
import com.pedido.dtos.pedidoProduto.avaliacao.PedidoAvaliacaoDTO;
import com.pedido.dtos.pedidoProduto.input.PedidoCompletoInputDTO;
import com.pedido.dtos.pedidoProduto.input.PedidoInput;
import com.pedido.dtos.pedidoProduto.input.PedidoSolicitacaoInputDTO;
import com.pedido.dtos.pedidoProduto.output.PedidoOutputDTO;
import com.pedido.dtos.pedidoProduto.output.ProdutoIncompletoDTO;
import com.pedido.infra.security.TokenService;
import com.pedido.model.Pedido;
import com.pedido.model.Produto;
import com.pedido.model.Status;
import com.pedido.repository.PedidoRepository;
import com.pedido.repository.ProdutoRepository;
import com.pedido.validations.validadorPedido.ValidadorPedidos;

import jakarta.servlet.http.HttpServletRequest;


@Service
public class PedidoService {


    @Autowired
    private PedidoRepository repository;

    @Autowired
    private ProdutoRepository produtoRepository;


    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private ValidadorPedidos validador;

    @Autowired
    private TokenService tokenService;

    public List<PedidoOutputDTO> verPedidos(HttpServletRequest request){
        var usuario = tokenService.extrairInformacoes(request);
        List<Pedido> pedidos;
        if (usuario.tipo().equals("Cliente")) {
              pedidos = repository.findAllByIdCliente(usuario.id());
        } else {
            pedidos = repository.findAllByIdLoja(usuario.id());
        }
        return pedidos.stream().map(PedidoOutputDTO::new).collect(Collectors.toList());
        
    }

    public void enviarSeparacao(PedidoInput dados, HttpServletRequest request) {
        var usuario = tokenService.extrairInformacoes(request);

        var pedido = new PedidoSolicitacaoInputDTO(usuario.id(), dados);
        rabbitTemplate.convertAndSend("pedido.solicitado", pedido);
    }
    
    public void pedidoSeparado( PedidoCompletoInputDTO dto) {
        var pedido = new Pedido(dto);

        var produtos = dto.produtos().stream().map(Produto::new).collect(Collectors.toList());
        produtoRepository.saveAll(produtos);

        pedido.adicionarProdutos(produtos);
        repository.save(pedido);
    }
   
    public void confirmarPedido(Long idPedido, HttpServletRequest request) {
        var usuario = tokenService.extrairInformacoes(request);
        validador.validarPatch(idPedido, usuario.id());

        var pedido = repository.getReferenceById(idPedido);
        var pagamentoDTO = new PagamentoInputDTO(pedido);
        rabbitTemplate.convertAndSend("pedido.concluido", pagamentoDTO);
        pedido.setStatus(Status.CONFIRMADO);
        repository.save(pedido);
    }
    
    @Transactional
    public void cancelarPedido(long idPedido, HttpServletRequest request) {
        var usuario = tokenService.extrairInformacoes(request);

        validador.validarDelete(idPedido, usuario.id());
        var pedido = repository.getReferenceById(idPedido);
        
        if (pedido.getStatus() == Status.SEPARADO | pedido.getStatus() == Status.CONFIRMADO ) {
            PedidoSolicitacaoInputDTO dtoPedido = new PedidoSolicitacaoInputDTO(pedido.getId(),
                pedido.getProdutos().stream().map(ProdutoIncompletoDTO::new).collect(Collectors.toList()));
            rabbitTemplate.convertAndSend("pedido.cancelado-produto", dtoPedido);       
        }
        if (pedido.getStatus() == Status.CONFIRMADO) {
            rabbitTemplate.convertAndSend("pedido.cancelado-pagamento", pedido.getId());
        }
        
        pedido.setStatus(Status.CANCELADO);
        repository.save(pedido);
    }
    
    @Transactional
    public void pagamentoConfirmado(Long idPedido) {
        var pedido = repository.getReferenceById(idPedido);
        pedido.setStatus(Status.PAGO); 
        repository.save(pedido);
        entregaPedido(idPedido);
    }    

    public void entregaPedido(Long idPedido){
        var pedido = repository.getReferenceById(idPedido);
        pedido.setStatus(Status.ENTREGUE); 
        rabbitTemplate.convertAndSend("pedido.entregue",new PedidoAvaliacaoDTO(pedido));
        repository.save(pedido);
    }

    @Transactional
    public void cancelarPedido(Long idPedido) {
        repository.deleteById(idPedido);
    }
}

