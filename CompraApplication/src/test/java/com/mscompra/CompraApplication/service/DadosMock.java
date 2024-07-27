package com.mscompra.CompraApplication.service;

import com.mscompra.CompraApplication.model.Pedido;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

@Service
public class DadosMock {
    public Pedido getPedido(){
        return Pedido.builder()
                .nome("Lucas Barros")
                .produto(1L)
                .valor(BigDecimal.TEN)
                .dataCompra(new Date())
                .cpfCliente("111.222.333-40")
                .cep("12345678")
                .email("lucas-barros28@hotmail.com")
                .build();
    }
}
