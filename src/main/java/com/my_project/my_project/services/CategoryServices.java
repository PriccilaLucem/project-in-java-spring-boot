package com.my_project.my_project.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my_project.my_project.entities.Category;
import com.my_project.my_project.repostories.CategoryRepository;

@Service
public class CategoryServices {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category getOneCategory(Long id) {
        Category category = categoryRepository.findById(id).orElse(new Category());
        return category;
    }
}
