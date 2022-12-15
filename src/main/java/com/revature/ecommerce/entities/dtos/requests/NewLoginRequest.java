package com.revature.ecommerce.entities.dtos.requests;

import com.revature.ecommerce.utils.utility_classes.PasswordHasher;

public class NewLoginRequest {

    private String email;
    private char[] password;

    public NewLoginRequest(String email, String password) {
        this.email = email;
        this.password = PasswordHasher.hash(password);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public char[] getPassword() {
        return password;
    }

    public void setPassword(char[] password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "NewLoginRequest{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
