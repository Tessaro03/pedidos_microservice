package com.pedido.model;

import com.pedido.dtos.pedidoProduto.input.ProdutoCompletoDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Produto {

    public Produto(ProdutoCompletoDTO dto){
        this.idProduto = dto.idProduto();
        this.idLoja = dto.idLoja();
        this.nomeProduto = dto.nomeProduto();
        this.valor = dto.valor();
        this.observacao = dto.observacao();
        this.quantidade = dto.quantidade();
        this.valorTotal = dto.quantidade() * dto.valor();
    }
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private Long idProduto;
    private Long idLoja;
    private String nomeProduto;
    private Double valor;
    private Double valorTotal;
    private String observacao;
    private Integer quantidade;

    @ManyToOne
    private Pedido pedido;
}
