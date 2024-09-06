package org.example.gc_coffee.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProductDTO extends BaseDTO {
    private String productId;
    private String productName;
    private String category;
    private long price;
    private String description;
}
