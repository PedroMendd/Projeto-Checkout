package com.workercompras.service.producer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.workercompras.model.Cartao;
import com.workercompras.model.Pedido;
import com.workercompras.service.CartaoService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.math.BigDecimal;

@RequiredArgsConstructor(onConstructor_ = @__(@Autowired))
@Service
public class PedidoProducer {

    private static final Logger log = LoggerFactory.getLogger(PedidoProducer.class);
    private final RabbitTemplate rabbitTemplate;
    private final Queue queue;
    private final ObjectMapper mapper;
    private final CartaoService cartaoService;

    @SneakyThrows
    @PostMapping
    public void enviarPedido(Pedido pedido) {
        pedido.setCartao(Cartao.builder()
                        .numero(cartaoService.gerarCartao())
                        .limiteDisponivel(cartaoService.gerarLimite())
                .build());

        rabbitTemplate.convertAndSend(queue.getName(), mapper.writeValueAsString(pedido));

    }

}
