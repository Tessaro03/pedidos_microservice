package com.pedido.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pedido.model.Item;

public interface ItemRepository extends JpaRepository<Item, Long>{

}
