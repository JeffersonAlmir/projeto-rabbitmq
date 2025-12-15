package com.app.consomer.api2.component;

import com.app.consomer.api2.entity.Produto;
import com.app.consomer.api2.service.ProdutoService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class QueueConsumer {

    private ProdutoService produtoService;

    public QueueConsumer(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @RabbitListener(queues = {"${app.rabbitmq.queue}"})
    public void receive(@Payload Produto produto) {
        produtoService.processar(produto);
    }
}
