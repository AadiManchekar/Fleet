package com.aadi.dummy_child_module.infrastructure.impl;

import com.aadi.dummy_child_module.spi.contract.KafkaMessagePublisher;
import com.aadi.dummy_child_module.spi.dto.KafkaMessageDTO;

import lombok.extern.slf4j.Slf4j;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class KafkaMessagePublisherImpl implements KafkaMessagePublisher {

    private final KafkaTemplate<String, Object> template;

    public KafkaMessagePublisherImpl(KafkaTemplate<String, Object> template) {
        this.template = template;
    }

    @Override
    public CompletableFuture<SendResult<String, Object>> send(String topic, KafkaMessageDTO message) {
        return template.send(topic, message);
    }
}
