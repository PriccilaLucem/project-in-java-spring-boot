package com.my_project.my_project.repostories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.my_project.my_project.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}