package com.my_project.my_project.repostories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.my_project.my_project.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
