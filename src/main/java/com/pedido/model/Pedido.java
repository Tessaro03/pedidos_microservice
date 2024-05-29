package com.pedido.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.pedido.dtos.ItemInputDTO;
import com.pedido.dtos.PedidoInputDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
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

    public Pedido(@Valid PedidoInputDTO dados ){
        this.nomeCliente = dados.nomeCliente();
        this.dataHora = LocalDateTime.now();
        this.status = Status.REALIZANDO;
        this.itens = dados.itens().stream().map(Item::new).collect(Collectors.toList());
        this.valorPedido = itens.stream().mapToDouble(Item::getValorTotal).sum();
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double valorPedido;

    private String nomeCliente;

    private LocalDateTime dataHora;

    @Enumerated
    private Status status;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Item> itens;


}
