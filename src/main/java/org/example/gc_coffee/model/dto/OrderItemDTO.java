package org.example.gc_coffee.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderItemDTO extends BaseDTO {
    private long seq;
    private String orderId;
    private String productId;
    private String category;
    private long price;
    private int quantity;
}
