package com.my_project.my_project.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.my_project.my_project.entities.User;
import com.my_project.my_project.entities.dto.UserDto;
import com.my_project.my_project.services.UserServices;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @Autowired
    private UserServices service;

    @GetMapping
    public ResponseEntity<List<UserDto>> getAll() {
        List<User> users = service.listAllUsers();
        List<UserDto> usersDtos = new ArrayList<>();
        
        for(int i = 0; i<users.size(); i++){
           usersDtos.add(users.get(i).createUserDto());
        }
        return ResponseEntity.ok().body(usersDtos);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        
        try{
            
            User user = service.getOneUser(id);
            return ResponseEntity.ok().body(user.createUserDto());

        }catch(EntityNotFoundException e){

            Map<String, String> response = new HashMap<>();
            response.put("error:", "User Not Found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        
    }

    @PostMapping
    public ResponseEntity<?> postUser(@RequestBody User user) {
        User newUser = service.insertUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser.createUserDto());
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        try {
            service.deleteUser(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } catch (DataIntegrityViolationException e) {
            Map<String, String> response = new HashMap<>();
            response.put("error", "Cannot delete user 1, there are orders attreling to user 1");
            return ResponseEntity.badRequest().body(response);

        } catch (EmptyResultDataAccessException e) {
            Map<String, String> response = new HashMap<>();
            response.put("error:", "User Not Found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> putUser(@PathVariable Long id, @RequestBody User patchItens) {
        try {

            User user = service.updateUser(id, patchItens);
            return ResponseEntity.accepted().body(user.createUserDto());
        } catch (EntityNotFoundException e) {
            Map<String, String> response = new HashMap<>();
            response.put("error:", "User Not Found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

    }

}
