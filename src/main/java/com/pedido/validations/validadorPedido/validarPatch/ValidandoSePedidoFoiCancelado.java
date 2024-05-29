package com.pedido.validations.validadorPedido.validarPatch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pedido.infra.exceptions.ValidacaoException;
import com.pedido.model.Status;
import com.pedido.repository.PedidoRepository;

@Service
public class ValidandoSePedidoFoiCancelado implements ValidadorPatchPedido{

    @Autowired
    private PedidoRepository repository;

    public void validar(Long id) {
        var pedido = repository.getReferenceById(id);
        if (pedido.getStatus() == Status.CANCELADO) {
            throw new ValidacaoException("Pedido foi cancelado");
        }
    }
    
}
