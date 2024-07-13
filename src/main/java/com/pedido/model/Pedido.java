package com.pedido.model;

import java.time.LocalDateTime;
import java.util.List;

import com.pedido.dtos.pedidoProduto.output.PedidoProdutosOutputDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "Pedido")
@Table(name = "pedidos")
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Pedido {

    public Pedido(@Valid PedidoProdutosOutputDTO dados){
        this.nomeCliente = dados.nomeCliente();
        this.dataHora = LocalDateTime.now();
        this.status = Status.PENDENTE;
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double valorPedido;

    private String nomeCliente;

    private LocalDateTime dataHora;

    @Enumerated
    private Status status;

    @OneToMany
    private List<Produto> produtos;
  


}
