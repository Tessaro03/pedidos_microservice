package com.pedido.dtos.pedidoProduto.avaliacao;

import com.pedido.model.Produto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProdutoAvaliacaoDTO(

    @NotNull
    long idProduto,
    
    @NotBlank
    String nome

) {

    public ProdutoAvaliacaoDTO(Produto produto){
        this(produto.getIdProduto(), produto.getNomeProduto());
    }

}
