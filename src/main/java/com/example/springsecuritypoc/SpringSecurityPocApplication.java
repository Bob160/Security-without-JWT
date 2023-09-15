package com.example.springsecuritypoc;

import com.example.springsecuritypoc.config.JwtRequestFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.filter.OncePerRequestFilter;

@SpringBootApplication
@ComponentScan(basePackages = "com.example.springsecuritypoc.config")
public class SpringSecurityPocApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityPocApplication.class, args);
    }

    @Bean
    public FilterRegistrationBean<OncePerRequestFilter> jwtFilter() {
        FilterRegistrationBean<OncePerRequestFilter> filterBean = new FilterRegistrationBean<>();
        filterBean.setFilter(new JwtRequestFilter());
        filterBean.addUrlPatterns("/api/*"); // Specify the URL patterns you want to protect
        return filterBean;
    }
}
