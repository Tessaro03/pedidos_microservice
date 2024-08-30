package com.pedido.validations.validadorPedido.validarDelete;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.pedido.infra.exceptions.ValidacaoException;
import com.pedido.model.Pedido;
import com.pedido.model.Status;
import com.pedido.repository.PedidoRepository;

public class ValidandoSePedidoJaFoiPagoTest {

    @InjectMocks
    private ValidandoSePedidoJaFoiPago validacao;

    @Mock
    private PedidoRepository repository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName(" Ocorre exception, pedido esta com status pago")
    void testValidarCase1() {
        Pedido pedido = new Pedido();
        pedido.setStatus(Status.PAGO);

        when(repository.getReferenceById(any())).thenReturn(pedido);

        assertThrows(ValidacaoException.class, () -> validacao.validar(any()));
    }

    @Test
    @DisplayName(" Não ocorre exception, pedido esta com status diferente de pago")
    void testValidarCase2() {
        Pedido pedido = new Pedido();
        pedido.setStatus(Status.SEPARADO);

        when(repository.getReferenceById(any())).thenReturn(pedido);

        assertDoesNotThrow( () -> validacao.validar(any()));
    }



}
