package com.pedido.amqp;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pedido.service.PedidoService;

@Service
public class PedidoAMPQListener {

    @Autowired
    private PedidoService service;

    @RabbitListener(queues="pagamento.confirmado-pedido") 
    public void pagamentoConcluido(Long idPedido) { 
        service.pagamentoConfirmado(idPedido);
    } 

    
}
