package com.appProducer.api1.service;

import com.appProducer.api1.entity.Produto;
import com.sun.jdi.PrimitiveValue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class QueueSendService {

    private final RabbitTemplate rabbitTemplate;
    private final String exchangeName;
    private final String routingKey;
    private final RedisTemplate<String, Object> redisTemplate;

    public QueueSendService(RabbitTemplate rabbitTemplate,
                            @Value("${app.rabbitmq.exchange}") String exchangeName,
                            @Value("${app.rabbitmq.routingkey}") String routingKey, RedisTemplate<String, Object> redisTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        this.exchangeName = exchangeName;
        this.routingKey = routingKey;
        this.redisTemplate = redisTemplate;
    }

    public void send(Produto produto){
        redisTemplate.delete("produto");
        log.info("Chave Produto Deletada");
        rabbitTemplate.convertAndSend(exchangeName, routingKey, produto);
    }


}

