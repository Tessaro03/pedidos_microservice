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
    private List<ValidadorPatchPedido> validadorPatch;

    @Autowired
    private List<ValidadorDeletePedido> validadorDelete;

    public void validarPatch(Long idPedido){
        validarId.validarId(idPedido);
        validadorPatch.forEach(v -> v.validar(idPedido));

    }

    public void validarDelete(Long idPedido){
        validarId.validarId(idPedido);
        validadorDelete.forEach(v -> v.validar(idPedido));

    }
 


}
