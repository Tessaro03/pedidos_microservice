package com.pedido.dtos.pedidoProduto.output;

import jakarta.validation.constraints.NotNull;

public record ProdutoIncompletoDTO(

    @NotNull
    Long id,

    @NotNull
    Integer quantidade,

    String observacao
) {

     

}
