package org.example.gc_coffee.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderDTO extends BaseDTO {
    private String orderId;
    private String email;
    private String address;
    private String postcode;
    private String orderStatus;
}
