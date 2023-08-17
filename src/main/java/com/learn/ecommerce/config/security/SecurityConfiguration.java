package com.learn.ecommerce.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests( (authorize) -> authorize
                .requestMatchers(HttpMethod.GET,"/auth/*").anonymous()
                .requestMatchers(HttpMethod.POST,"/auth/*").anonymous()
                .requestMatchers("/auth/logout").authenticated()
                .requestMatchers("/admin/product/*").hasAuthority("ROLE_ADMIN")
                .requestMatchers("/admin/product").hasAuthority("ROLE_ADMIN")
                .requestMatchers("/admin/*").hasAuthority("ROLE_ADMIN")
                .requestMatchers("/admin").hasAuthority("ROLE_ADMIN")
                .anyRequest().authenticated()
        ).formLogin( (form) -> form
                .loginPage("/auth/signin")
                .defaultSuccessUrl("/", true)
//                .failureUrl("/error")
        ).csrf(AbstractHttpConfigurer::disable)
                .logout( (logout) -> logout
                    .logoutUrl("/auth/signout")
                    .clearAuthentication(true)
                    .deleteCookies("JSESSIONID")
                );
//        ).exceptionHandling( (ex) -> ex
//                .accessDeniedPage("/error")
//        );
        return http.build();
    }

//    @Bean
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService)
//                .passwordEncoder(passwordEncoder());
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
