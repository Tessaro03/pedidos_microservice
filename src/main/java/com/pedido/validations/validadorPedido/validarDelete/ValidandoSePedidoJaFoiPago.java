package com.pedido.validations.validadorPedido.validarDelete;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pedido.infra.exceptions.ValidacaoException;
import com.pedido.model.Status;
import com.pedido.repository.PedidoRepository;

@Service
public class ValidandoSePedidoJaFoiPago implements ValidadorDeletePedido{

    @Autowired
    private PedidoRepository repository;

    public void validar(Long idPedido) {
        var pedido = repository.getReferenceById(idPedido);
        if (pedido.getStatus() == Status.PAGO) {
            throw new ValidacaoException("Pedido já foi pago não pode ser cancelado");
        }
    }
    
}
