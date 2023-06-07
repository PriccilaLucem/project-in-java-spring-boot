package com.my_project.my_project.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my_project.my_project.entities.User;
import com.my_project.my_project.repostories.UserRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserServices {
    @Autowired
    private UserRepository userRepository;

    public List<User> listAllUsers() {
        return userRepository.findAll();
    }

    public User getOneUser(Long id) {
       return userRepository.findById(id).get();
    }

    public User insertUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);

    }

    public User updateUser(User user) {
        return userRepository.save(user);

    }

    public User updateUser(Long id, User user) {
        User entity = userRepository.getReferenceById(id);
        updateData(entity, user);
        return userRepository.save(entity);
    }

    private void updateData(User entity, User obj) {
        entity.setName(obj.getName());
        entity.setEmail(obj.getEmail());
        entity.setPhone(obj.getPhone());
    }

    public User getByEmail(String email){
        User user = userRepository.findByEmail(email);
        if(user == null){
            throw new EntityNotFoundException("Email not exists");
        };
        return user;
    }
}
