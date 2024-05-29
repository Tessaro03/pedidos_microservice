package com.pedido.validations.validarIdPedido;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pedido.infra.exceptions.ValidacaoException;
import com.pedido.repository.PedidoRepository;

@Service
public class ValidarIdPedido {
    
    @Autowired
    private PedidoRepository repository;

    public  void validarId(Long id){
        if (!repository.existsById(id)) {
            throw new ValidacaoException("Id do pedido não existe"); 
        }

    }


}
