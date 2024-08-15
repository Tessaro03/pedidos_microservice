package com.pedido.dtos;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record PagamentoInputDTO(
    
    @NotBlank
    Long idCliente,
    
    Double valor,

    @NotNull
    Long pedidoId
) 
{}
