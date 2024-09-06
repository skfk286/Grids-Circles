package org.example.gc_coffee.model.service;

import org.example.gc_coffee.model.repository.OrderItemRepository;
import org.example.gc_coffee.model.repository.OrderRepository;
import org.example.gc_coffee.model.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired private OrderRepository orderRepository;
    @Autowired private OrderItemRepository orderItemRepository;
    @Autowired private ProductRepository productRepository;

    // 주문하기

}
