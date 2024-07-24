package com.worker.validador.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.worker.validador.exceptions.LimiteIndisponivelException;
import com.worker.validador.exceptions.SaldoInsuficienteException;
import com.worker.validador.model.Pedido;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Slf4j
@RequiredArgsConstructor(onConstructor_ = @__(@Autowired))
@Component
public class Consumer {

    private final ObjectMapper mapper;
    private final ValidadorService validadorService;


    @RabbitListener(queues = {"${queue.name}"})
    public void consumer(@Payload Message message) throws IOException {

        var pedido = mapper.readValue(message.getBody(), Pedido.class);
        log.info("Pedido recebido no Validador: {}", pedido);

        try {
            validadorService.validarPedido(pedido);
        } catch (LimiteIndisponivelException | SaldoInsuficienteException exception) {
            exception.printStackTrace();
        }
    }

}
