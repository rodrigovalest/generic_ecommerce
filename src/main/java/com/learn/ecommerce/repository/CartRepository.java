package com.learn.ecommerce.repository;

import com.learn.ecommerce.model.cart.CartModel;
import com.learn.ecommerce.model.product.ProductModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CartRepository extends JpaRepository<CartModel, UUID> {
    CartModel findByUserUserId(UUID userId);
}