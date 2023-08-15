package com.learn.ecommerce.controller;

import com.learn.ecommerce.model.product.ProductCategoriesEnum;
import com.learn.ecommerce.model.product.ProductDto;
import com.learn.ecommerce.model.product.ProductModel;
import com.learn.ecommerce.repository.ProductRepository;
import com.learn.ecommerce.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;
import java.util.UUID;

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
            @RequestParam(value = "page", defaultValue = "0") int page) {
        ModelAndView mv = new ModelAndView("admin/product");
        Page<ProductModel> pageProduct = productService.pageProducts(page, 12, product);
        mv.addObject("pageProduct", pageProduct);

        return mv;
    }

    @GetMapping("/product/{id}")
    public ModelAndView findAll(@PathVariable UUID id) {
        Optional<ProductModel> product = productRepository.findById(id);

        if (product.isPresent()) {
            ModelAndView mv = new ModelAndView("admin/show");
            mv.addObject("product", product.get());
            return mv;
        } else {
            return new ModelAndView("redirect:/error");
        }
    }
}