package org.example.gc_coffee.model.service;

import org.example.gc_coffee.common.util.LoggerUtil;
import org.example.gc_coffee.common.util.UUIDUtil;
import org.example.gc_coffee.model.dto.ProductDTO;
import org.example.gc_coffee.model.entity.ProductEntity;
import org.example.gc_coffee.model.repository.ProductRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductService {
    private static final Logger logger = LoggerUtil.getLogger();

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

    // 상품 등록 하기
    public List<ProductEntity> createProduct(ProductDTO productDTO) {
        ProductEntity productEntity = productDTO.toProductEntity();

        UUID productId = UUIDUtil.generateUUIDv1();
        productEntity.setProductId(productId);
        productRepository.save(productEntity);

        return findAllProducts();
    }

    // 상품 수정 하기
    public void modifyProduct(ProductDTO productDTO) {
        ProductEntity productEntity = findProductById(productDTO.getProductId());

        productEntity.setProductName(productDTO.getProductName());
        productEntity.setDescription(productDTO.getDescription());
        productEntity.setPrice(productDTO.getPrice());
        productEntity.setCategory(productDTO.getCategory());

        productRepository.saveAndFlush(productEntity);
    }

    // 상품 삭제 하기
    public void deleteProduct(UUID productId) {
        ProductEntity productEntity = findProductById(productId);

        productRepository.delete(productEntity);
    }
}
