package com.pedido.dtos.pedidoProduto.output;

import com.pedido.model.Produto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record ProdutoIncompletoDTO(

    @NotNull
    Long id,

    @NotNull
    @Min(1)
    Integer quantidade,

    String observacao
) {

    public ProdutoIncompletoDTO(Produto produto){
        this(produto.getIdProduto(), produto.getQuantidade(), produto.getObservacao());
    }

}
