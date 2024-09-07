package org.example.gc_coffee.model.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "products")
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
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

