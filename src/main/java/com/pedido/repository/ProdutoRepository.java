package com.pedido.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pedido.model.Produto;

public interface ProdutoRepository extends  JpaRepository<Produto, Long>{
    
}
