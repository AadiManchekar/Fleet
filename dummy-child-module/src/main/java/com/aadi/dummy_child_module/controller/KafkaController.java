package com.aadi.dummy_child_module.controller;

import com.aadi.dummy_child_module.api.contract.MessagePublisher;
import com.aadi.dummy_child_module.api.dto.BasicMessageDTO;
import com.aadi.dummy_child_module.controller.dto.ComplexMessageDTO;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/kafka/producer")
public class KafkaController {

    private final MessagePublisher kafkaMessagePublisher;

    public KafkaController(MessagePublisher kafkaMessagePublisher) {
        this.kafkaMessagePublisher = kafkaMessagePublisher;
    }

    @PostMapping("/publish")
    public ResponseEntity<Boolean> publishMessage(@RequestBody ComplexMessageDTO message) {
        BasicMessageDTO basicMessage =
                BasicMessageDTO.builder().message(message.getMessage()).build();
        kafkaMessagePublisher.publishMessage(basicMessage);

        return null;
    }
}
