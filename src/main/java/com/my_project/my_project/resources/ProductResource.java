package com.my_project.my_project.resources;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.my_project.my_project.entities.Product;
import com.my_project.my_project.services.ProductServices;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping(value = "/products")
public class ProductResource {

    @Autowired
    private ProductServices services;

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok().body(services.getProducts());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getOneProduct(@PathVariable Long id) {
        try{
            Product product = services.getProduct(id);
            return ResponseEntity.ok().body(product);

        } catch(EntityNotFoundException e){
            
            Map<String, String> response = new HashMap<>();
            response.put("error:", "User Not Found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } 
        
    }
}
