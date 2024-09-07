package org.example.gc_coffee.model.service;

import org.example.gc_coffee.model.entity.ProductEntity;
import org.example.gc_coffee.model.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductService {

    @Autowired private ProductRepository productRepository;
    
    // 상품 목록 전체 보기
    public List<ProductEntity> findAllProducts() {
        return productRepository.findAll();
    }

    // 상품 목록 상세 보기
    public ProductEntity findProductById(UUID productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + productId));
    }

    // 상품 등록 하기 (관리자)

    // 상품 삭제 하기 (관리자)
}
