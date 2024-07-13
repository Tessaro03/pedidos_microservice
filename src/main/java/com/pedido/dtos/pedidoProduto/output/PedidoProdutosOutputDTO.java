package com.pedido.dtos.pedidoProduto.output;

import java.util.List;


public record PedidoProdutosOutputDTO(

    String nomeCliente,
    
    List<ProdutoIncompletoDTO> produtoPedidos
    

) {


}
