package com.pedido.dtos.pedidoProduto.avaliacao;

import java.util.List;
import java.util.stream.Collectors;

import com.pedido.model.Pedido;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PedidoAvaliacaoDTO(

    @NotNull
    Long idPedido,

    @NotBlank
    List<ProdutoAvaliacaoDTO> produtos

) {

    public PedidoAvaliacaoDTO(Pedido pedido) {
        this(pedido.getId() ,pedido.getProdutos().stream().map(ProdutoAvaliacaoDTO::new).collect(Collectors.toList()));
    }
    
}
