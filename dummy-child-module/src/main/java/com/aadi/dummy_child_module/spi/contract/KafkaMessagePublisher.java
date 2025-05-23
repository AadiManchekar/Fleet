package com.aadi.dummy_child_module.spi.contract;

import com.aadi.dummy_child_module.spi.dto.KafkaMessageDTO;

import org.springframework.kafka.support.SendResult;

import java.util.concurrent.CompletableFuture;

public interface KafkaMessagePublisher {
    CompletableFuture<SendResult<String, Object>> send(String topic, KafkaMessageDTO message);
}
