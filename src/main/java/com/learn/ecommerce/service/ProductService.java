package com.learn.ecommerce.service;

import com.learn.ecommerce.model.product.ProductModel;
import com.learn.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public Page<ProductModel> pageProducts(int page, int size, String productName) {
        Pageable pageable = PageRequest.of(page, size);

        if (productName != null) {
            return productRepository.findByNameContaining(pageable, productName);
        }

        return productRepository.findAll(pageable);
    }
}
