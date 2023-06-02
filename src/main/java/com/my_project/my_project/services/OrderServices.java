package com.my_project.my_project.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my_project.my_project.entities.Order;
import com.my_project.my_project.repostories.OrderRepository;

@Service
public class OrderServices {
    @Autowired
    private OrderRepository orderRepository;

    public List<Order> listAllUsers() {
        return orderRepository.findAll();
    }

    public Order getOneUser(Long id) {
        Order user = orderRepository.findById(id).orElse(new Order());
        return user;
    }
}
