package com.learn.ecommerce.model.cart;

import com.learn.ecommerce.model.user.UserModel;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "TB_CART")
public class CartModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID cartId;
    @OneToOne
    @JoinColumn(name = "user_id")
    private UserModel user;

    public CartModel() {
    }

    public CartModel(UserModel user) {
        this.user = user;
    }

    public UUID getCartId() {
        return cartId;
    }

    public void setCartId(UUID cartId) {
        this.cartId = cartId;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }
}
