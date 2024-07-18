package com.pedido.dtos.pedidoProduto.input;

import java.util.List;

import com.pedido.dtos.pedidoProduto.output.ProdutoIncompletoDTO;

public record PedidoInput(

    List<ProdutoIncompletoDTO> produtos

    ) {
    
}
