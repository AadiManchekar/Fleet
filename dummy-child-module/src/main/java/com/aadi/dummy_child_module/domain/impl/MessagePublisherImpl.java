package com.aadi.dummy_child_module.domain.impl;

import com.aadi.dummy_child_module.api.contract.MessagePublisher;
import com.aadi.dummy_child_module.api.dto.BasicMessageDTO;
import com.aadi.dummy_child_module.spi.contract.KafkaMessagePublisher;
import com.aadi.dummy_child_module.spi.dto.KafkaMessageDTO;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MessagePublisherImpl implements MessagePublisher {

    private final KafkaMessagePublisher kafkaMessagePublisher;

    public MessagePublisherImpl(KafkaMessagePublisher kafkaMessagePublisher) {
        this.kafkaMessagePublisher = kafkaMessagePublisher;
    }

    @Override
    public void publishMessage(BasicMessageDTO messageDTO) {

        KafkaMessageDTO kafkaMessageDTO =
                KafkaMessageDTO.builder().message(messageDTO.getMessage()).build();

        kafkaMessagePublisher
                .send("internal-producer-topic", kafkaMessageDTO)
                .whenComplete(
                        (result, exception) -> {
                            if (exception != null) {
                                log.error("Failed to send message: {}", kafkaMessageDTO, exception);
                            } else {
                                log.info(
                                        "Message {} sent successfully to topic: {} on partition: {}"
                                                + " with offset: {}",
                                        kafkaMessageDTO,
                                        result.getRecordMetadata().topic(),
                                        result.getRecordMetadata().partition(),
                                        result.getRecordMetadata().offset());
                            }
                        });
    }
}
