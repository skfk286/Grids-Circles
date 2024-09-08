package org.example.gc_coffee.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.gc_coffee.enums.OrderStatus;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "orders")
@Data
@EqualsAndHashCode(callSuper = true)
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

    @Enumerated(EnumType.STRING)
    @Column(length = 50, nullable = false)
    private OrderStatus orderStatus;
}
