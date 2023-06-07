package com.my_project.my_project.entities;
import java.util.Objects;

public class Login {
    
    private String password;
    private String email;


    public Login() {
    }

    public Login(String password, String email) {
        this.password = password;
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Login password(String password) {
        setPassword(password);
        return this;
    }

    public Login email(String email) {
        setEmail(email);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Login)) {
            return false;
        }
        Login login = (Login) o;
        return Objects.equals(password, login.password) && Objects.equals(email, login.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(password, email);
    }

    @Override
    public String toString() {
        return "{" +
            " password='" + getPassword() + "'" +
            ", email='" + getEmail() + "'" +
            "}";
    }
    
}
