package com.revature.ecommerce.entities.dtos.requests;

public class NewUserRequest {

    private String email;
    private String password1;
    private String password2;
    private char[] hashedPassword;
    private String givenName;
    private String surname;

    public NewUserRequest(String email, String password1, String password2, String givenName, String surname) {
        this.email = email;
        this.password1 = password1;
        this.password2 = password2;
        this.hashedPassword = null;
        this.givenName = givenName;
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword1() {
        return password1;
    }

    public void setPassword1(String password1) {
        this.password1 = password1;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public char[] getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(char[] hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public String toString() {
        return "NewUserRequest{" +
                "email='" + email + '\'' +
                ", password1='" + password1 + '\'' +
                ", password2='" + password2 + '\'' +
                ", givenName='" + givenName + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
}
