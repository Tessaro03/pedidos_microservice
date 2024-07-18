package com.pedido.dtos.pedidoProduto.output;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.pedido.dtos.pedidoProduto.input.ProdutoCompletoDTO;
import com.pedido.model.Pedido;
import com.pedido.model.Status;

public record PedidoOutputDTO(
    Long id,
    LocalDateTime dataHora,
    Status status,
    Double valorTotal,
    List<ProdutoCompletoDTO> produtos
) {
    
    public PedidoOutputDTO(Pedido pedido){
        this(pedido.getId(), pedido.getDataHora(), pedido.getStatus(), pedido.getValorPedido(),
         pedido.getProdutos().stream().map(ProdutoCompletoDTO::new).collect(Collectors.toList()));
    }

}
