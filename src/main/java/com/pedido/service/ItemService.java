package com.pedido.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pedido.dtos.ItemInputDTO;
import com.pedido.model.Item;
import com.pedido.repository.ItemRepository;
import com.pedido.repository.PedidoRepository;


@Service
public class ItemService {


    @Autowired
    private ItemRepository repository;

    @Autowired
    private PedidoRepository pedidoRepository;

    public void adicionarItem(Long id, ItemInputDTO dto){
        var pedido = pedidoRepository.getReferenceById(id);
        var item = new Item(dto);

        pedido.getItens().add(item);
        repository.save(item);
    }

}
