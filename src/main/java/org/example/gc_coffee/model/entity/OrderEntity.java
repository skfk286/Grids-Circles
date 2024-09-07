package org.example.gc_coffee.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "orders")
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "BINARY(16)")
    private UUID orderId;

    @Column(length = 50, nullable = false)
    private String email;

    @Column(length = 200, nullable = false)
    private String address;

    @Column(length = 200, nullable = false)
    private String postcode;

    @Column(length = 50, nullable = false)
    private String orderStatus;
}
