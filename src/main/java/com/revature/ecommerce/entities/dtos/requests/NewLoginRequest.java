package com.revature.ecommerce.entities.dtos.requests;

import com.revature.ecommerce.utils.utility_classes.PasswordHasher;

public class NewLoginRequest {

    private String email;
    private String password;

    public NewLoginRequest() {
        super();
    }

    public NewLoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
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
