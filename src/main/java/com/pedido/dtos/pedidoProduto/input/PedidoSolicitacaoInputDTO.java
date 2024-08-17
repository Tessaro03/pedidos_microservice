package com.pedido.dtos.pedidoProduto.input;

import java.util.List;

import com.pedido.dtos.pedidoProduto.output.ProdutoIncompletoDTO;



public record PedidoSolicitacaoInputDTO(
    
    Long idCliente,

    List<ProdutoIncompletoDTO> produtos


) {

    public PedidoSolicitacaoInputDTO(Long idCliente ,PedidoInput produtosIncompleto){
        this(idCliente, produtosIncompleto.produtos());
    }
    
   
}
