package com.my_project.my_project.resources;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

import com.my_project.my_project.entities.User;
import com.my_project.my_project.services.UserServices;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @Autowired
    private UserServices service;

    @GetMapping
    public ResponseEntity<List<User>> getAll() {
        List<User> users = service.listAllUsers();

        return ResponseEntity.ok().body(users);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        User user = service.getOneUser(id);

        if (user.getId() == null) {
            Map<String, String> response = new HashMap<>();
            response.put("error:", "User Not Found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } else {
            return ResponseEntity.ok().body(user);
        }
    }

    @PostMapping
    public ResponseEntity<?> postUser(@RequestBody User user) {
        User newUser = service.insertUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        User user = service.getOneUser(id);
        System.out.println(user);
        if (user.getId() == null) {
            Map<String, String> response = new HashMap<>();
            response.put("error:", "User Not Found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        try {

            service.deleteUser(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (ConstraintViolationException e) {
            Map<String, String> response = new HashMap<>();
            response.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }

    }

}
