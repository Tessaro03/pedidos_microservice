package com.pedido.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ItemInputDTO(

    @NotBlank
    String nomeProduto,

    @NotNull
    Integer quantidade,
    
    @NotNull
    Double valor,
    String observacao
) {


}
