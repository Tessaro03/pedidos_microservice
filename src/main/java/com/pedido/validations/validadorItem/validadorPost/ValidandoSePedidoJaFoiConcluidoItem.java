package com.pedido.validations.validadorItem.validadorPost;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pedido.dtos.ItemInputDTO;
import com.pedido.infra.exceptions.ValidacaoException;
import com.pedido.model.Status;
import com.pedido.repository.PedidoRepository;

@Service
public class ValidandoSePedidoJaFoiConcluidoItem implements ValidadorPostItem{
    
    @Autowired
    private PedidoRepository repository;

    public void validar(long idPedido, ItemInputDTO dto) {
        var pedido = repository.getReferenceById(idPedido);
        if (pedido.getStatus() != Status.REALIZANDO) {
            throw new ValidacaoException("Pedido não aceita novo item");
        }
    }
}
