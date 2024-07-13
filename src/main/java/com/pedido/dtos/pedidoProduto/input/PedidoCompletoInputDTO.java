package com.pedido.dtos.pedidoProduto.input;

import java.util.List;



public record PedidoCompletoInputDTO(
    
    Long idPedido,

    List<ProdutoCompletoDTO> produtos


) {
    
   
}
