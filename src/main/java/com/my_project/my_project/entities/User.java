package com.my_project.my_project.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import org.mindrot.jbcrypt.BCrypt;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.my_project.my_project.entities.dto.UserDto;
import com.my_project.my_project.resources.exceptions.PasswordException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "client")
    private List<Order> orders = new ArrayList<>();
    @Column(unique = true)
    private String email;
    private String phone;
    private String password;

    public User() {
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getPassword() {
        return password;
    }
    public User(Long id, String name, String email, String phone, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        String salt = BCrypt.gensalt();
        String hashPassword = BCrypt.hashpw(password, salt);

        this.password = hashPassword;
    }

    @PrePersist
    public void beforeInsert() {
        String salt = BCrypt.gensalt();
        String hashPassword = BCrypt.hashpw(password, salt);
        this.password = hashPassword;
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User name(String name) {
        setName(name);
        return this;
    }

    public User email(String email) {
        setEmail(email);
        return this;
    }

    public User phone(String phone) {
        setPhone(phone);
        return this;
    }

    public void verifyPassword(String password){
        if(!BCrypt.checkpw(password, this.password)){
            throw new PasswordException("Incorrect password");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof User)) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(name, user.name) && Objects.equals(email, user.email)
                && Objects.equals(phone, user.phone) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, password);
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", name='" + getName() + "'" +
                ", email='" + getEmail() + "'" +
                ", phone='" + getPhone() + "'" +
                "}";
    }

    public UserDto createUserDto(){
        return new UserDto(id, name, email, phone);
    }

}
