package com.pedido.dtos;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.pedido.model.Pedido;
import com.pedido.model.Status;

public record PedidoOutputDTO(
    Long id,
    LocalDateTime dataHora,
    Status status,
    List<ItemOutputDTO> itens
) {
    
    public PedidoOutputDTO(Pedido pedido){
        this(pedido.getId(), pedido.getDataHora(), pedido.getStatus(),
             pedido.getItens().stream().map(ItemOutputDTO::new).collect(Collectors.toList()));
    }

}
