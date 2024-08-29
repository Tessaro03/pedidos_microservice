package com.pedido.dtos.pedidoProduto.output;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

public class ProdutoIncompletoDTOTest {

    private static Validator validator;
    
    @BeforeAll
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
    
    @Test
    void quantidadeMenorQueMin() {
        ProdutoIncompletoDTO dto = new ProdutoIncompletoDTO(1l, -1, null);
        Set<ConstraintViolation<ProdutoIncompletoDTO>> violations = validator.validate(dto);
        
        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());
        
        ConstraintViolation<ProdutoIncompletoDTO> violation = violations.iterator().next();
        assertEquals("deve ser maior que ou igual à 1", violation.getMessage());
    }

    @Test
    void quantidadeMaiorQueMin() {
        ProdutoIncompletoDTO dto = new ProdutoIncompletoDTO(1l, 2, null);
        Set<ConstraintViolation<ProdutoIncompletoDTO>> violations = validator.validate(dto);
        
        assertTrue(violations.isEmpty());
    }

    @Test
    void quantidadeIgualMin() {
        ProdutoIncompletoDTO dto = new ProdutoIncompletoDTO(1l, 1, null);
        Set<ConstraintViolation<ProdutoIncompletoDTO>> violations = validator.validate(dto);
        
        assertTrue(violations.isEmpty());
    }



}
