package com.pedido.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pedido.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long>{
    
}
