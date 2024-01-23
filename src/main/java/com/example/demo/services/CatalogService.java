package com.example.demo.services;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.demo.dto.MessageCaterogy;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class CatalogService {

    private static final Logger logger = LoggerFactory.getLogger(RabbitMQProducerService.class);
    // this is a listener
    @RabbitListener(queues = "${rabbitmq.queue.name}")
    public void consume(String messageCaterogy) {

        logger.info("Received message from RabbitMQ test service consumer: " + messageCaterogy);
    }
}

