package com.pedido.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pedido.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long>{

    @Query("SELECT p FROM Pedido p WHERE p.idCliente = :idCliente")
    List<Pedido> findAllByIdCliente(Long idCliente);

    @Query("SELECT p FROM Pedido p WHERE p.idLoja = :idLoja")
    List<Pedido> findAllByIdLoja(Long idLoja);
    
}
