package com.my_project.my_project.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.my_project.my_project.entities.Product;
import com.my_project.my_project.repostories.ProductRepository;

public class ProductServices {

    @Autowired
    ProductRepository productRepository;

    public List<Product> getUsers() {
        return productRepository.findAll();
    }

    public Product getUser(Long id) {
        return productRepository.findById(id).orElse(new Product());
    }
}
