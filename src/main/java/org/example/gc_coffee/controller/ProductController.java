package org.example.gc_coffee.controller;

import org.example.gc_coffee.common.api.ApiResponse;
import org.example.gc_coffee.common.util.LoggerUtil;
import org.example.gc_coffee.model.dto.ProductDTO;
import org.example.gc_coffee.model.entity.ProductEntity;
import org.example.gc_coffee.model.service.ProductService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product")
@CrossOrigin
public class ProductController {
    private static final Logger logger = LoggerUtil.getLogger();

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<?> getAllProducts() {
        logger.info("Request to get all products");

        List<ProductEntity> productEntityList = productService.findAllProducts();

        if (productEntityList.isEmpty()) {
            logger.warn("No products found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(false, "No products found", null, HttpStatus.NOT_FOUND.value()));
        }

        List<ProductDTO> productDTOList = productEntityList.stream()
                .map(ProductDTO::new)
                .collect(Collectors.toList());

        logger.info("Found {} products", productEntityList.size());
        return ResponseEntity.ok(new ApiResponse<>(true, "Products retrieved successfully", productDTOList, HttpStatus.OK.value()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable UUID id) {
        logger.info("Request to get product by id: {}", id);

        ProductEntity productEntity = productService.findProductById(id);

        if (productEntity == null) {
            logger.warn("Product not found with id: {}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(false, "Product not found with id: " + id, null, HttpStatus.NOT_FOUND.value()));
        }

        logger.info("Product found: {}", productEntity.getProductName());
        ProductDTO productDTO = new ProductDTO(productEntity);
        return ResponseEntity.ok(new ApiResponse<>(true, "Product retrieved successfully", productDTO, HttpStatus.OK.value()));
    }

    // 상품 등록
    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody ProductDTO productDTO) {
        logger.info("Request to create product: {}", productDTO);

        List<ProductEntity> productEntityList = productService.createProduct(productDTO);

        List<ProductDTO> productDTOList = productEntityList.stream()
                .map(ProductDTO::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(new ApiResponse<>(true, "Product created successfully", productDTOList, HttpStatus.OK.value()));
    }

    // 상품 수정
    @PutMapping
    public ResponseEntity<?> updateProduct(@RequestBody ProductDTO productDTO) {
        logger.info("Request to update product: {}", productDTO);

        productService.modifyProduct(productDTO);

        return ResponseEntity.ok(new ApiResponse<>(true, "Product updated successfully", null, HttpStatus.OK.value()));
    }

    @DeleteMapping
    public ResponseEntity<?> deleteProduct(@RequestBody ProductDTO productDTO) {
        logger.info("Request to delete product: {}", productDTO);

        productService.deleteProduct(productDTO.getProductId());

        return ResponseEntity.ok(new ApiResponse<>(true, "Product deleted successfully", null, HttpStatus.OK.value()));
    }

}

