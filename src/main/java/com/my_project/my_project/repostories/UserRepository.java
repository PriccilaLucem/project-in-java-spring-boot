package com.my_project.my_project.repostories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.my_project.my_project.models.User;

public interface UserRepository extends JpaRepository<User, Long> {

}