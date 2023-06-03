package com.my_project.my_project.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

import com.my_project.my_project.entities.Category;
import com.my_project.my_project.services.CategoryServices;

@RestController
@RequestMapping(value = "orders")
public class CategoryResource {

    @Autowired
    private CategoryServices service;

    @GetMapping
    public ResponseEntity<List<Category>> getAll() {
        List<Category> categories = service.listAllUsers();

        return ResponseEntity.ok().body(categories);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        Category category = service.getOneUser(id);

        if (category.getId() == null) {
            Map<String, String> response = new HashMap<>();
            response.put("error:", "Order Not Found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } else {
            return ResponseEntity.ok().body(service.getOneUser(id));
        }
    }

}