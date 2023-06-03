package com.my_project.my_project.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my_project.my_project.entities.User;
import com.my_project.my_project.repostories.UserRepository;

@Service
public class UserServices {
    @Autowired
    private UserRepository userRepository;

    public List<User> listAllUsers() {
        return userRepository.findAll();
    }

    public User getOneUser(Long id) {
        User user = userRepository.findById(id).orElse(new User());
        return user;
    }

    public User insertUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);

    }
}
