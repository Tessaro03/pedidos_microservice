package com.pedido.model;

import java.time.LocalDateTime;
import java.util.List;

import com.pedido.dtos.pedidoProduto.input.PedidoCompletoInputDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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

    public Pedido(PedidoCompletoInputDTO dto) {
        this.idCliente = dto.idCliente();
        this.idLoja = dto.produtos().get(0).idLoja();
        this.dataHora = LocalDateTime.now();
        this.status = Status.PENDENTE;  
     }
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long idCliente;
    
    private Long idLoja;
    
    private Double valorPedido;
    
    private LocalDateTime dataHora;
    
    @Enumerated
    private Status status;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Produto> produtos;

    
    public void adicionarProdutos(List<Produto> produtos) {
        this.setProdutos(produtos);
        this.valorTotal(produtos);
        this.setStatus(Status.SEPARADO);
    }
    public void valorTotal(List<Produto> produtos) {
        var valorTotalPedido = produtos.stream().mapToDouble(Produto::getValorTotal).sum();
        this.setValorPedido(valorTotalPedido);
    }
  


}
