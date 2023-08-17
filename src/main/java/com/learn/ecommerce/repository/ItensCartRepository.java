package com.learn.ecommerce.repository;

import com.learn.ecommerce.model.cart.CartModel;
import com.learn.ecommerce.model.cart.ItensCartModel;
import com.learn.ecommerce.model.product.ProductModel;
import org.aspectj.apache.bcel.classfile.Module;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ItensCartRepository extends JpaRepository<ItensCartModel, UUID> {
    Boolean existsByProduct(ProductModel product);
    Optional<ItensCartModel> getByProduct(ProductModel product);
    List<ItensCartModel> getByCart(CartModel cart);
}
