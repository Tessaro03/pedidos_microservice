package com.pedido.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pedido.dtos.PedidoInputDTO;
import com.pedido.service.PedidoService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/pedidos")
public class PedidoController {
    
    
    @Autowired
    private PedidoService service;

    
    @GetMapping
    public ResponseEntity verPedidos(){
        return ResponseEntity.ok().body(service.verPedidos());
    }


    @PostMapping
    public void criarPedido(@RequestBody @Valid PedidoInputDTO pedido){
        service.criar(pedido);
    }

    @PatchMapping("/{idPedido}/confirmado")
    public void confirmarPedido(@PathVariable Long idPedido){
        service.confirmarPedido(idPedido);
    }

    @PutMapping("/{idPedido}/pago")
    public void pagamentoConfirmado(@PathVariable Long idPedido){
        service.pagamentoConfirmado(idPedido);
    }

    @DeleteMapping("{idPedido}")
    public void cancelarPedido(@PathVariable long idPedido){
        service.cancelarPedido(idPedido);
    }

}
