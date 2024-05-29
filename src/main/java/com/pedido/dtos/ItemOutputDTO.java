package com.pedido.dtos;

import com.pedido.model.Item;

public record ItemOutputDTO(
    Long id,
    String nomeProduto,
    Integer quantidade,
    Double valor,
    String observacao
) {
    public ItemOutputDTO(Item item){
        this(item.getId(),item.getNomeProduto(),item.getQuantidade(), item.getValor(),item.getObservacao());
    }

}
