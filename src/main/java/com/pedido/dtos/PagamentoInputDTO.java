package com.pedido.dtos;


import com.pedido.model.Pedido;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record PagamentoInputDTO(
    
    @NotBlank
    Long idCliente,
    
    @NotBlank
    Long idLoja,

    Double valor,

    @NotNull
    Long pedidoId
) 
{
    public PagamentoInputDTO(Pedido pedido){
        this(pedido.getIdCliente(), pedido.getIdLoja(), pedido.getValorPedido(), pedido.getId());
    }

}
