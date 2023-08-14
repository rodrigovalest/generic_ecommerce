package com.learn.ecommerce.controller;

import com.learn.ecommerce.model.product.ProductCategoriesEnum;
import com.learn.ecommerce.model.product.ProductDto;
import com.learn.ecommerce.model.product.ProductModel;
import com.learn.ecommerce.model.user.UserRoleEnum;
import com.learn.ecommerce.repository.ProductRepository;
import com.learn.ecommerce.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.awt.print.Pageable;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductService productService;

    @GetMapping
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("admin/index");
        return mv;
    }

    @GetMapping("product/new")
    public ModelAndView formCreate() {
        ModelAndView mv = new ModelAndView("admin/new");
        mv.addObject("productDto", new ProductDto());
        mv.addObject("productCategories", ProductCategoriesEnum.values());
        return mv;
    }

    @PostMapping("/product")
    public ModelAndView create(@Valid ProductDto productDto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return new ModelAndView("redirect:/admin/product/new");
        } else {
            ProductModel product = productDto.toProduct();
            productRepository.save(product);

            return new ModelAndView("redirect:/admin");
        }
    }

    @GetMapping("/product")
    public ModelAndView findAll(
            @RequestParam(value = "product", required = false) String product,
            Pageable pageable) {
        ModelAndView mv = new ModelAndView("admin/product");
        List<ProductModel> allProducts;

        if (product != null) {
            allProducts = productRepository.findByNameContaining(product);
        } else {
            allProducts = productRepository.findAll();
        }

        mv.addObject("products", allProducts);

        return mv;
    }
}