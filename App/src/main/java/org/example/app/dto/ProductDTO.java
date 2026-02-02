package org.example.app.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter @Setter
public class ProductDTO {
    private Long id;
    private String name;
    private String description;
    private Double basePrice;
    private String categoryName;
    private List<ProductVariantDTO> variants;
}