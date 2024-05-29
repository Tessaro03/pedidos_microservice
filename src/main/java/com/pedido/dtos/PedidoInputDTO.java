package com.pedido.dtos;

import java.util.List;

import jakarta.validation.constraints.NotNull;

public record PedidoInputDTO(

    String nomeCliente,

    @NotNull
    List<ItemInputDTO> itens

) {


}
