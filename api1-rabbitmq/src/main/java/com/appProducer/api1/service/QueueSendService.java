package com.appProducer.api1.service;

import com.appProducer.api1.entity.Produto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class QueueSendService {

    private final RabbitTemplate rabbitTemplate;
    private final String exchangeName;
    private final String routingKey;

    public QueueSendService(RabbitTemplate rabbitTemplate,
                            @Value("${app.rabbitmq.exchange}") String exchangeName,
                            @Value("${app.rabbitmq.routingkey}") String routingKey) {
        this.rabbitTemplate = rabbitTemplate;
        this.exchangeName = exchangeName;
        this.routingKey = routingKey;
    }

    public void send(Produto produto){
        rabbitTemplate.convertAndSend(exchangeName, routingKey, produto);
    }
}

