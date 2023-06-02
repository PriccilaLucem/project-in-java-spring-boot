package com.my_project.my_project.config;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.my_project.my_project.entities.Category;
import com.my_project.my_project.entities.Order;
import com.my_project.my_project.entities.User;
import com.my_project.my_project.entities.enums.OrderStatus;
import com.my_project.my_project.repostories.CategoryRepository;
import com.my_project.my_project.repostories.OrderRepository;
import com.my_project.my_project.repostories.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public void run(String... args) throws Exception {
        User user1 = new User(null, "name", "email.com", "phone", "123456");
        userRepository.save(user1);
        Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), OrderStatus.WAITING_PAYMENT, user1);
        orderRepository.save(o1);

        Category category = new Category(null, "Eletronics");
        categoryRepository.save(category);
    }

}