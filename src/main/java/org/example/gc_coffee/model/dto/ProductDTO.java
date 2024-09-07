package org.example.gc_coffee.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.gc_coffee.model.entity.ProductEntity;

import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
public class ProductDTO extends BaseDTO {
    private UUID productId;
    private String productName;
    private String category;
    private long price;
    private String description;

    public ProductDTO(ProductEntity productEntity) {
        this.productId = productEntity.getProductId();
        this.productName = productEntity.getProductName();
        this.category = productEntity.getCategory();
        this.price = productEntity.getPrice();
        this.description = productEntity.getDescription();
    }

    public ProductEntity toProductEntity() {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setProductId(productId);
        productEntity.setProductName(productName);
        productEntity.setCategory(category);
        productEntity.setPrice(price);
        productEntity.setDescription(description);
        return productEntity;
    }
}
