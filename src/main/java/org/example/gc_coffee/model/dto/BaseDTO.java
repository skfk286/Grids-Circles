package org.example.gc_coffee.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BaseDTO {
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
