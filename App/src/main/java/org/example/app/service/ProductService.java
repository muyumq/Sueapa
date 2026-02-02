package org.example.app.service;

import org.example.app.dto.ProductDTO;
import java.util.List;

public interface ProductService {

    List<ProductDTO> findAllProducts();

    ProductDTO findProductById(Long id);

    ProductDTO createProduct(ProductDTO productDTO);
}