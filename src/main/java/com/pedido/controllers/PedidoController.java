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

import com.pedido.dtos.pedidoProduto.input.PedidoInput;
import com.pedido.service.PedidoService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/pedidos")
public class PedidoController {
    
    
    @Autowired
    private PedidoService service;

    
    @GetMapping
    @Operation(summary = "Ver Pedidos", description = "Retorna os pedidos criados")
    public ResponseEntity verPedidos(HttpServletRequest request){
        return ResponseEntity.ok().body(service.verPedidos(request));
    }

    @PostMapping
    @Operation(summary = "Criar Pedido", description = "Cria o pedido")
    public void criarPedido(@RequestBody @Valid PedidoInput pedido, HttpServletRequest request){
        service.enviarSeparacao(pedido, request);
    }

    @PatchMapping("/{idPedido}/confirmado")
    @Operation(summary = "Confirmar Pedido", description = "Confirma o pedido e envia msg para pagamentos criar um pagamento")
    public void confirmarPedido(@PathVariable Long idPedido,  HttpServletRequest request){
        service.confirmarPedido(idPedido, request);
    }

    @DeleteMapping("/{idPedido}")
    @Operation(summary = "Deleta Pedido", description = "Deleta o pedido e envia msg para pagamentos cancelar o pagamento e para produtos ajustar o estoque")
    public void cancelarPedido(@PathVariable long idPedido, HttpServletRequest request){
        service.cancelarPedido(idPedido, request);
    }
}
