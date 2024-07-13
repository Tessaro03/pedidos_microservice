package com.pedido.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pedido.dtos.PedidoInputDTO;
import com.pedido.service.PedidoService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/pedidos")
public class PedidoController {
    
    
    @Autowired
    private PedidoService service;

    
    @GetMapping
    @Operation(summary = "Ver Pedidos", description = "Retorna os pedidos criados")
    public ResponseEntity verPedidos(){
        return ResponseEntity.ok().body(service.verPedidos());
    }


    @PostMapping
    @Operation(summary = "Criar Pedido", description = "Cria o pedido")
    public void criarPedido(@RequestBody @Valid PedidoInputDTO pedido){
        service.criar(pedido);
    }

    @PatchMapping("/{idPedido}/confirmado")
    @Operation(summary = "Confirmar Pedido", description = "Confirma o pedido e envia msg para pagamentos criar um pagamento")
    public void confirmarPedido(@PathVariable Long idPedido){
        service.confirmarPedido(idPedido);
    }

    @DeleteMapping("/{idPedido}")
    @Operation(summary = "Deleta Pedido", description = "Deleta o pedido e envia msg para pagamentos cancelar o pagamento")
    public void cancelarPedido(@PathVariable long idPedido){
        service.cancelarPedido(idPedido);
    }

}
