package com.pedido.validations.validadorPedido;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pedido.validations.validadorPedido.validarDelete.ValidadorDeletePedido;
import com.pedido.validations.validadorPedido.validarIdPedido.ValidarIdPedido;
import com.pedido.validations.validadorPedido.validarPatch.ValidadorPatchPedido;

@Service
public class ValidadorPedidos {
    
    @Autowired
    private ValidarIdPedido validarId;

    @Autowired
    private List<ValidadorDeletePedido> validadorDelete;

    @Autowired
    private List<ValidadorPatchPedido> validadorPatch;
    
    public void validarPatch(Long idPedido, Long idUsuario){
        validarId.validarId(idPedido);
        validarId.validarSePedidoEDeUsuario(idPedido, idUsuario);
        validadorPatch.forEach(v -> v.validar(idPedido));
    }

    public void validarDelete(Long idPedido, Long idUsuario){
        validarId.validarId(idPedido);
        validarId.validarSePedidoEDeUsuario(idPedido, idUsuario);
        validadorDelete.forEach(v -> v.validar(idPedido));
    }

}
