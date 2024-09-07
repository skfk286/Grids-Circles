package org.example.gc_coffee.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.gc_coffee.enums.OrderStatus;
import org.example.gc_coffee.model.entity.OrderEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
public class OrderDTO extends BaseDTO {
    private UUID orderId;
    private String email;
    private String address;
    private String postcode;
    private OrderStatus orderStatus;

    private List<ProductDTO> products;

    public OrderDTO() {}

    public OrderDTO(OrderEntity orderEntity) {
        this.orderId = orderEntity.getOrderId();
        this.email = orderEntity.getEmail();
        this.address = orderEntity.getAddress();
        this.postcode = orderEntity.getPostcode();
        this.orderStatus = orderEntity.getOrderStatus();
        super.setCreatedAt(orderEntity.getCreatedAt());
        super.setUpdatedAt(orderEntity.getUpdatedAt());
    }

    public OrderEntity toOrderEntity() {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderId(orderId);
        orderEntity.setEmail(email);
        orderEntity.setAddress(address);
        orderEntity.setPostcode(postcode);
        orderEntity.setOrderStatus(orderStatus);
        return orderEntity;
    }
}
