package com.pedido.model;


import com.pedido.dtos.ItemInputDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "Item")
@Table(name = "ItensPedido")
@Getter
@Setter
@NoArgsConstructor  
@EqualsAndHashCode(of = "id")
public class Item {

    public Item(ItemInputDTO item){
        this.nomeProduto = item.nomeProduto();
        this.quantidade = item.quantidade();
        this.valor = item.valor();
        this.observacao = item.observacao();
        this.valorTotal = valor * quantidade;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer quantidade;

    private String nomeProduto;

    private Double valor;
    
    private Double valorTotal;

    private String observacao;


}
