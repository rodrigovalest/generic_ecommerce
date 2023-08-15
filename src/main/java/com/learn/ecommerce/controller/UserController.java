package com.learn.ecommerce.controller;

import com.learn.ecommerce.model.product.ProductModel;
import com.learn.ecommerce.model.user.UserModel;
import com.learn.ecommerce.repository.ProductRepository;
import com.learn.ecommerce.repository.UserRepository;
import com.learn.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;
import java.util.UUID;

@Controller
public class UserController {
    @Autowired
    ProductService productService;
    @Autowired
    ProductRepository productRepository;

    @GetMapping("/")
    public ModelAndView index(Authentication authentication) {
        ModelAndView mv = new ModelAndView("user/home");

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        if (userDetails.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            mv.addObject("adminCard", true);
        } else {
            mv.addObject("adminCard", false);
        }

        return mv;
    }

    @GetMapping("/profile")
    public ModelAndView profile(Authentication authentication) {
        UserModel user = (UserModel) authentication.getPrincipal();

        ModelAndView mv = new ModelAndView("user/profile");
        mv.addObject("user", user);
        return mv;
    }

    @GetMapping("/product")
    public ModelAndView search(
            @RequestParam(value = "search") String search,
            @RequestParam(value = "page", defaultValue = "0") int page
    ) {
        ModelAndView mv = new ModelAndView("user/search");
        Page<ProductModel> pageProduct = productService.pageProducts(page, 12, search);
        mv.addObject("search", search);
        mv.addObject("pageProduct", pageProduct);

        return mv;
    }

    @GetMapping("/product/{id}")
    public ModelAndView show(@PathVariable UUID id) {
        Optional<ProductModel> product = productRepository.findById(id);

        ModelAndView mv;
        if (product.isPresent()) {
            mv = new ModelAndView("user/show");
            mv.addObject("product", product.get());
        } else {
            mv = new ModelAndView("redirect:/error");
        }
        return mv;
    }
}
