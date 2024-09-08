package org.example.gc_coffee.model.service;

import org.example.gc_coffee.common.util.LoggerUtil;
import org.example.gc_coffee.common.util.UUIDUtil;
import org.example.gc_coffee.enums.OrderStatus;
import org.example.gc_coffee.model.dto.OrderDTO;
import org.example.gc_coffee.model.dto.ProductDTO;
import org.example.gc_coffee.model.entity.OrderEntity;
import org.example.gc_coffee.model.entity.OrderItemEntity;
import org.example.gc_coffee.model.repository.OrderItemRepository;
import org.example.gc_coffee.model.repository.OrderRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class OrderService {
    private static final Logger logger = LoggerUtil.getLogger();

    @Autowired private OrderRepository orderRepository;
    @Autowired private OrderItemRepository orderItemRepository;

    // 주문하기
    @Transactional
    public void saveOrderAndProducts(OrderDTO orderDTO) {
        UUID orderUUID = UUIDUtil.generateUUIDv1();
        logger.info("order created UUID: {}", orderUUID);

        orderDTO.setOrderId(orderUUID); // 신규 주문 UUID 생성
        orderDTO.setOrderStatus(OrderStatus.PROCESSING); // 주문 - 처리상태 설정

        OrderEntity orderEntity = orderDTO.toOrderEntity();
        OrderEntity orderEntityResult = orderRepository.save(orderEntity);

        int orderProductSize = orderDTO.getProducts().size();
        for (int i = 0; i < orderProductSize; i++) {
            ProductDTO productDTO = orderDTO.getProducts().get(i);
            OrderItemEntity orderItemEntity = new OrderItemEntity();

            orderItemEntity.setOrderEntity(orderEntityResult);
            orderItemEntity.setProductEntity(productDTO.toProductEntity());
            orderItemEntity.setPrice(productDTO.getPrice()); // 가격 * 개수
            orderItemEntity.setQuantity(productDTO.getQuantity());
            orderItemEntity.setCategory(productDTO.getCategory());

            orderItemRepository.save(orderItemEntity);
        }
    }
}
