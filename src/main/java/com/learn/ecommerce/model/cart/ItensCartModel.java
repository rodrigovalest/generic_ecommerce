package com.learn.ecommerce.model.cart;

import com.learn.ecommerce.model.product.ProductModel;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "TB_CART_PRODUCT")
public class ItensCartModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductModel product;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private CartModel cart;

    @Column(nullable = false)
    private Integer quantity;

    public ItensCartModel() {
    }

    public ItensCartModel(ProductModel product, CartModel cart, Integer quantity) {
        this.product = product;
        this.cart = cart;
        this.quantity = quantity;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public ProductModel getProduct() {
        return product;
    }

    public void setProduct(ProductModel product) {
        this.product = product;
    }

    public CartModel getCart() {
        return cart;
    }

    public void setCart(CartModel cart) {
        this.cart = cart;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
