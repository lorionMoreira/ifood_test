package com.example.demo.services;

import com.example.demo.dto.MessageCaterogy;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQProducerService {

    private static final Logger logger = LoggerFactory.getLogger(RabbitMQProducerService.class);

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing.key}")
    private String routingKey;

    @Value("${rabbitmq.routing.json.key}")
    private String routingJsonKey;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMessage2(MessageCaterogy messageRabbitMQ) {
        logger.info("Sending message: " + messageRabbitMQ);
        rabbitTemplate.convertAndSend(exchange,routingJsonKey,messageRabbitMQ);
        logger.info("Message sent to exchange: " + exchange + " with routing key: " + routingKey);

    }
    @CacheEvict(value = "catalogCache", key = "#ownerId")
    public void sendMessage(String ownerId) {
        logger.info("Sending message: " + ownerId);
        rabbitTemplate.convertAndSend(exchange,routingKey,ownerId);
        logger.info("Message sent to exchange: " + exchange + " with routing key: " + routingKey);

    }


}
