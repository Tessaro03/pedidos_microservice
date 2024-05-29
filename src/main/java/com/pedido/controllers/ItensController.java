package com.pedido.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pedido.dtos.ItemInputDTO;
import com.pedido.service.ItemService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("item")
public class ItensController {
    
    @Autowired
    private ItemService service;

    @PostMapping("/{idPedido}")
    public void adicionarItem(@PathVariable Long idPedido,@RequestBody @Valid ItemInputDTO dto){
        service.adicionarItem(idPedido, dto);
    }

}
