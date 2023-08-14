package com.learn.ecommerce.model.product;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ProductDto {
    @NotBlank
    @NotNull
    private String name;

    @NotBlank
    @NotNull
    private String description;

    @NotNull
    @DecimalMin(value = "0.01", message = "Price must be greater than 0")
    private Float price;

    @NotNull
    @Min(value = 1, message = "Stock must be greater than 0")
    private Integer stock;

    @NotNull
    private ProductCategoriesEnum category;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public ProductCategoriesEnum getCategory() {
        return category;
    }

    public void setCategory(ProductCategoriesEnum category) {
        this.category = category;
    }

    public ProductModel toProduct() {
        ProductModel productModel = new ProductModel();
        productModel.setName(this.name);
        productModel.setDescription(this.description);
        productModel.setPrice(this.price);
        productModel.setStock(this.stock);
        productModel.setCategory(this.category);

        return productModel;
    }
}
