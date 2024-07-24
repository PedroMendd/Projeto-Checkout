package com.worker.validador.service;

import com.worker.validador.exceptions.LimiteIndisponivelException;
import com.worker.validador.exceptions.SaldoInsuficienteException;
import com.worker.validador.model.Cartao;
import com.worker.validador.model.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValidadorService {

    @Autowired
    private EmailService emailService;

    public void validarPedido (Pedido pedido) {
        validarLimiteDisponivel(pedido.getCartao());
        validarCompraComLimite(pedido);
        emailService.notificarClienteCompraComSucesso(pedido.getEmail());
    }

    private void validarCompraComLimite(Pedido pedido) {

        if (pedido.getValor().longValue() > pedido.getCartao().getLimiteDisponivel().longValue())
            throw new SaldoInsuficienteException("Você não tem limite para efetuar essa compra");

    }

    private void validarLimiteDisponivel(Cartao cartao) {

        if (cartao.getLimiteDisponivel().longValue() <= 0 )

            throw new LimiteIndisponivelException("Limite indisponível!");

    }

}
