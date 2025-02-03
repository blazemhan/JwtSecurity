package com.blazemhan.demosecurity.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {

    private record Product(int productId, String productName, double productPrice) {

    }

    // create a list of product
    public List<Product> products = new ArrayList<>(
            List.of(new Product(1, "Iphone 16", 1800.00),
                    new Product(2, "MacBook Pro", 4000.00),
                    new Product(3, "Samsung S24", 2100.00))
    );

    // function to return all products
    @GetMapping("/products")
    public List<Product> getProducts() {

        return products;
    }
    // function to add a new product
    @PostMapping("/add")
    public Product addProduct(@RequestBody Product product) {
        products.add(product);
        return product;

    }

    @GetMapping("/csrf")
    public CsrfToken getCsrfToken(HttpServletRequest request) {
        return (CsrfToken) request.getAttribute("_csrf");
    }
}
