package com.pedido.infra.exceptions;

public class ValidacaoException extends RuntimeException{

    public ValidacaoException(String mensagem){
        super(mensagem);
    }

}
