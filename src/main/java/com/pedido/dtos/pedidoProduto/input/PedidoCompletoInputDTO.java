package com.pedido.dtos.pedidoProduto.input;

import java.util.List;



public record PedidoCompletoInputDTO(
    
    Long idCliente,

    List<ProdutoCompletoDTO> produtos


) {

  
}
