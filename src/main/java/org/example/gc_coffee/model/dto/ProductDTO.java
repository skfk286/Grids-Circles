package org.example.gc_coffee.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
public class ProductDTO extends BaseDTO {
    private UUID productId;
    private String productName;
    private String category;
    private long price;
    private String description;
}
