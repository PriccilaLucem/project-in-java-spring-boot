package com.my_project.my_project.resources;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.my_project.my_project.entities.Login;
import com.my_project.my_project.entities.User;
import com.my_project.my_project.entities.dto.UserDto;
import com.my_project.my_project.resources.exceptions.PasswordException;
import com.my_project.my_project.services.UserServices;

import jakarta.persistence.EntityNotFoundException;


@RestController
@RequestMapping("/login")
public class LoginResource {

    @Autowired
    private UserServices userServices;

    @PostMapping
    public ResponseEntity<?> login(@RequestBody Login login){
        Map<String,String> response = new HashMap<>();

        try{
            User user = userServices.getByEmail(login.getEmail());
            user.verifyPassword(login.getPassword());
            
            UserDto obj = user.createUserDto();

            String token = Login.generateToken(obj);

            response.put("token: ", token);
            
            return ResponseEntity.ok().body(response);
        
        }catch(EntityNotFoundException e){
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }catch(PasswordException e){
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}
