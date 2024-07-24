package com.mscompra.CompraApplication.service;

import com.mscompra.CompraApplication.model.Pedido;
import com.mscompra.CompraApplication.repository.PedidoRepository;
import com.mscompra.CompraApplication.service.rabbitmq.Producer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class PedidoServiceTest {

    @InjectMocks
    private PedidoService pedidoService;

    @Mock
    private PedidoRepository pedidoRepository;

    @Mock
    private Producer producer;

    private DadosMock mock = new DadosMock();

    @DisplayName("Salvar pedido com sucesso")
    @Test
    void deveSalvarUmPedidoComSucesso() {
        var pedidoMock = mock.getPedido();

        Mockito.when(pedidoRepository.save(Mockito.any(Pedido.class))).thenReturn(pedidoMock);
        Mockito.doNothing().when(producer).enviarPedido(Mockito.any(Pedido.class));

       var pedidoSalvo = pedidoService.salvar(pedidoMock);

       assertEquals(pedidoMock.getCep(), pedidoSalvo.getCep());
       assertNotNull(pedidoSalvo.getCep());
    }



}
