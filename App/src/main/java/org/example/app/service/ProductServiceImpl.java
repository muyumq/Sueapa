package org.example.app.service;

import org.example.app.dto.ProductDTO;
import org.example.app.dto.ProductVariantDTO;
import org.example.app.exception.ResourceNotFoundException;
import org.example.app.model.Product;
import org.example.app.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public List<ProductDTO> findAllProducts() {
        return productRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDTO findProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
        return convertToDto(product);
    }

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        // ในอนาคตเราจะเพิ่ม Logic การแปลง DTO -> Entity เพื่อบันทึกลง DB ที่นี่
        // ตอนนี้ใส่โครงไว้ก่อนครับ
        return null;
    }

    // ย้าย Private Method แปลงข้อมูลมาไว้ที่นี่ เพื่อให้ Controller สะอาด
    private ProductDTO convertToDto(Product product) {
        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setBasePrice(product.getBasePrice());
        dto.setCategoryName(product.getCategory() != null ? product.getCategory().getName() : null);

        if (product.getVariants() != null) {
            dto.setVariants(product.getVariants().stream().map(v -> {
                ProductVariantDTO vDto = new ProductVariantDTO();
                vDto.setSku(v.getSku());
                vDto.setColor(v.getColor());
                vDto.setSize(v.getSize());
                vDto.setStockQuantity(v.getStockQuantity());
                vDto.setImageUrl(v.getImageUrl());
                return vDto;
            }).collect(Collectors.toList()));
        }
        return dto;
    }
}