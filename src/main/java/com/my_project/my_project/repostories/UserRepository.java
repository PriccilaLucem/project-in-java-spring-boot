package com.my_project.my_project.repostories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.my_project.my_project.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
    public User findByEmail(String email);
}