
package com.pedido.dtos.pedidoProduto.input;

import com.pedido.model.Produto;

public record ProdutoCompletoDTO(

    Long idProduto,
    Long idLoja,
    String nomeProduto,
    Double valor,
    Integer quantidade,
    String observacao

) {

    public ProdutoCompletoDTO(Produto produto){
        this(produto.getId(), produto.getIdLoja(), produto.getNomeProduto(),
         produto.getValor(), produto.getQuantidade(), produto.getObservacao());
    }
}
