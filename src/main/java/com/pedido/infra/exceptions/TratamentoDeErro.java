package com.pedido.infra.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import feign.FeignException;
import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class TratamentoDeErro {
    
    @ExceptionHandler(EntityNotFoundException.class)  
    public ResponseEntity tratarErro404() {
        return ResponseEntity.notFound().build();
    }


    @ExceptionHandler(ValidacaoException.class)
    public ResponseEntity tratarErroRegraDeNegocio(ValidacaoException ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(FeignException.class)
    public ResponseEntity tratarErroRequesição(FeignException ex){
        return ResponseEntity.status(ex.status()).build();
    }
    
}
