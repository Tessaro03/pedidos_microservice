package com.pedido.amqp;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pedido.service.PedidoService;

@Service
public class PedidoAMPQListener {

    @Autowired
    private PedidoService pedidoService;

    @RabbitListener(queues = "pagamento.concluido") 
    public void pagamentoConcluido(Long idPedido) { 
        pedidoService.confirmarPedido(idPedido);
    } 

    
}
