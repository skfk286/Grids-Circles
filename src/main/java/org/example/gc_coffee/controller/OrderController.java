package org.example.gc_coffee.controller;

import org.example.gc_coffee.common.api.ApiResponse;
import org.example.gc_coffee.common.util.LoggerUtil;
import org.example.gc_coffee.common.util.UUIDUtil;
import org.example.gc_coffee.enums.OrderStatus;
import org.example.gc_coffee.model.dto.OrderDTO;
import org.example.gc_coffee.model.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@CrossOrigin
public class OrderController {
    private static final Logger logger = LoggerUtil.getLogger();

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<?> order(@RequestBody OrderDTO orderDTO) {
        logger.info("Request to Order");

        orderService.saveOrderAndProducts(orderDTO);

        return ResponseEntity.ok(new ApiResponse<>(true, "request for payment has been completed.", null, HttpStatus.OK.value()));
    }
    
    // 모든 주문 조회
}
