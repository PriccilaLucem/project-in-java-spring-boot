package com.my_project.my_project.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.my_project.my_project.models.User;

@RestController
@RequestMapping(value = "users")
public class UserResource {

    @GetMapping
    public ResponseEntity<User> getAll() {
        User user = new User(1L, "name", "email@gmail.com", "teste", "teste");
        return ResponseEntity.ok().body(user);
    }

}
