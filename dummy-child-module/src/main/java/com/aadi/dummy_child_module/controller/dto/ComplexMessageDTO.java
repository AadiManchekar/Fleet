package com.aadi.dummy_child_module.controller.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ComplexMessageDTO {
    String a;
    Boolean b;
    String message;
}
