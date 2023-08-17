package com.learn.ecommerce.controller;

import com.learn.ecommerce.model.cart.CartModel;
import com.learn.ecommerce.model.user.UserDto;
import com.learn.ecommerce.model.user.UserDtoLogin;
import com.learn.ecommerce.model.user.UserModel;
import com.learn.ecommerce.model.user.UserRoleEnum;
import com.learn.ecommerce.repository.CartRepository;
import com.learn.ecommerce.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
//@RequestMapping("/auth")
public class AuthController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    CartRepository cartRepository;

    @GetMapping("/auth/signin")
    public ModelAndView formLogin() {
        ModelAndView mv = new ModelAndView("auth/signIn");
        mv.addObject("userDtoLogin", new UserDtoLogin());
        return mv;
    }

    @PostMapping("/auth/signin")
    public ModelAndView login(@Valid UserDtoLogin userDtoLogin, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("redirect:/auth/signin");
        }

        userDtoLogin.setPassword(new BCryptPasswordEncoder().encode(userDtoLogin.getPassword()));
        UserModel user = userRepository.findByUsernameAndPassword(userDtoLogin.getUsername(), userDtoLogin.getPassword());

        if (user != null) {
            return new ModelAndView("redirect:/");
        }

        return new ModelAndView("redirect:/auth/signin");
    }

    @GetMapping("/auth/signup")
    public ModelAndView formSignUp() {
        ModelAndView mv = new ModelAndView("auth/signUp");
        mv.addObject("userDto", new UserDto());
        return mv;
    }

    @PostMapping("/auth/signup")
    public ModelAndView signUp(@Valid UserDto userDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("redirect:/auth/signup");
        }

        if (userRepository.existsByUsername(userDto.getUsername()) || userRepository.existsByEmail(userDto.getEmail())) {
            return new ModelAndView("redirect:/auth/signup");
        }

        UserModel user = userDto.toUser();
        user.setPassword(new BCryptPasswordEncoder().encode(userDto.getPassword()));
        user.setRole(UserRoleEnum.ROLE_USER);
        userRepository.save(user);

        cartRepository.save(new CartModel(user));

        return new ModelAndView("redirect:/auth/signin");
    }

    @PostMapping("/auth/signout")
    public ModelAndView logout() {
        return new ModelAndView("redirect:/auth/signin");
    }
}
