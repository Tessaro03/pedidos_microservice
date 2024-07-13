package com.pedido.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Produto {
    
    private Long id;

    private Long idProduto;
    private Long idLoja;
    private String nomeProduto;
    private Double valor;
    private String observacao;
    private Integer quantidade;

    @ManyToOne
    private Pedido pedido;
}
