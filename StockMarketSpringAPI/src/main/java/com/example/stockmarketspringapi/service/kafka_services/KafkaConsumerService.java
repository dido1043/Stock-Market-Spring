package com.example.stockmarketspringapi.service.kafka_services;

import com.example.stockmarketspringapi.model.dto.CurrencyDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {
    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumerService.class);

    @KafkaListener(topics = "currency_topic", groupId = "currency_group")
    public void consumeMessage(String message) {
        logger.info("Consumed message: {}", message);
    }

}
