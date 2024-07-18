package com.pedido.dtos.pedidoProduto.input;

import java.util.List;

import com.pedido.dtos.pedidoProduto.output.ProdutoIncompletoDTO;



public record PedidoSolicitacaoInputDTO(
    
    Long idPedido,

    List<ProdutoIncompletoDTO> produtos


) {

    public PedidoSolicitacaoInputDTO(Long idPedido, PedidoInput produtosIncompleto){
        this(idPedido, produtosIncompleto.produtos());
    }
    
   
}
