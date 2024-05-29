package com.pedido.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pedido.dtos.PagamentoInputDTO;
import com.pedido.dtos.PedidoInputDTO;
import com.pedido.dtos.PedidoOutputDTO;
import com.pedido.model.Pedido;
import com.pedido.model.Status;
import com.pedido.repository.PedidoRepository;
import com.pedido.validations.validadorPedido.ValidadorPedidos;

import jakarta.validation.Valid;


@Service
public class PedidoService {


    @Autowired
    private PedidoRepository repository;


    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private ValidadorPedidos validador;

    public List<PedidoOutputDTO> verPedidos(){
        var pedidos = repository.findAll();
        return pedidos.stream().map(PedidoOutputDTO::new).collect(Collectors.toList());
    }

    public void criar(@Valid PedidoInputDTO dados) {
        var pedido = new Pedido(dados);
        repository.save(pedido);
    }

    public void confirmarPedido(Long idPedido) {
        validador.validarPatch(idPedido);
        var pedido = repository.getReferenceById(idPedido);
        var pagamentoDTO = new PagamentoInputDTO(pedido.getNomeCliente(), pedido.getValorPedido(), pedido.getId());
        rabbitTemplate.convertAndSend("pedido.concluido", pagamentoDTO);
        pedido.setStatus(Status.CONFIRMADO);
        repository.save(pedido);
    }
    
    public void cancelarPedido(long idPedido) {
        validador.validarDelete(idPedido);
        var pedido = repository.getReferenceById(idPedido);
        if (pedido.getStatus() == Status.CONFIRMADO) {
            rabbitTemplate.convertAndSend("pedido.cancelado",pedido.getId());
        }
        pedido.setStatus(Status.CANCELADO);
        repository.save(pedido);
    }
    
    public void pagamentoConfirmado(Long idPedido) {
        validador.validarPatch(idPedido);
        var pedido = repository.getReferenceById(idPedido);
        pedido.setStatus(Status.PAGO);
        repository.save(pedido);
    }
    
}

