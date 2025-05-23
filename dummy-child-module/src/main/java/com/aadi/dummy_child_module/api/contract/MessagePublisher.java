package com.aadi.dummy_child_module.api.contract;

import com.aadi.dummy_child_module.api.dto.BasicMessageDTO;

public interface MessagePublisher {
    void publishMessage(BasicMessageDTO message);
}
