package org.example.app.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ProductVariantDTO {
    private String sku;
    private String color;
    private String size;
    private Integer stockQuantity;
    private String imageUrl;
}