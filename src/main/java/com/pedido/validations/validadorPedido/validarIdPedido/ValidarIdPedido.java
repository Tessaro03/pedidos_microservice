package com.pedido.validations.validadorPedido.validarIdPedido;


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

    public void validarSePedidoEDeUsuario(Long idPedido, Long idUsuario){
        var pedido = repository.getReferenceById(idPedido);
        
        if (!pedido.getIdCliente().equals(idUsuario)) {
            throw new ValidacaoException("Pedido não pertence ao usuário"); 
        }
    }

}
