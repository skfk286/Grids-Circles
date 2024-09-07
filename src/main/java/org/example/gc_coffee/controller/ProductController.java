package org.example.gc_coffee.controller;

import org.example.gc_coffee.model.dto.ProductDTO;
import org.example.gc_coffee.model.entity.ProductEntity;
import org.example.gc_coffee.model.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product")
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
d
    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<?> getAllProducts() {
        logger.info("Request to get all products");

        List<ProductEntity> productEntityList = productService.findAllProducts();
        logger.info("Found {} products", productEntityList.size());

        return ResponseEntity.ok().body(productEntityList.stream()
                .map(ProductDTO::new)
                .collect(Collectors.toList())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable UUID id) {
        logger.info("Request to get product by id: {}", id);

        ProductEntity productEntity = productService.findProductById(id);

        if (productEntity != null) {
            logger.info("Product found: {}", productEntity.getProductName());
        } else {
            logger.warn("Product not found with id: {}", id);
        }

        return ResponseEntity.ok().body(new ProductDTO(productEntity));
    }
}

