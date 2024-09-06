//package org.example.gc_coffee.model.entity;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@Entity
//@Table(name = "order_items")
//@Data
//@Builder
//@AllArgsConstructor
//@NoArgsConstructor
//public class OrderItemEntity extends BaseEntity {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long seq;
//
//    @ManyToOne
//    @JoinColumn(name = "order_id", nullable = false)
//    private OrderEntity order;
//
//    @ManyToOne
//    @JoinColumn(name = "product_id", nullable = false)
//    private ProductEntity product;
//
//    @Column(length = 50, nullable = false)
//    private String category;
//
//    @Column(nullable = false)
//    private Long price;
//
//    @Column(nullable = false)
//    private Integer quantity;
//}
