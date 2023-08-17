package com.learn.ecommerce.model.cart;

import com.learn.ecommerce.model.product.ProductModel;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ItensCartDto {
    @NotBlank
    @NotNull
    private ProductModel product;
    @NotBlank
    @NotNull
    @Min(value = 1)
    private Integer quantity;

    public ProductModel getProduct() {
        return product;
    }

    public void setProduct(ProductModel product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
