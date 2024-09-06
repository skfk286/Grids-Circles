package org.example.gc_coffee.model.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "products")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductEntity extends BaseEntity {

    @Id
    @Column(columnDefinition = "BINARY(16)")
    private UUID productId;

    @Column(length = 20, nullable = false)
    private String productName;

    @Column(length = 50, nullable = false)
    private String category;

    @Column(nullable = false)
    private Long price;

    @Column(length = 500)
    private String description;
}
