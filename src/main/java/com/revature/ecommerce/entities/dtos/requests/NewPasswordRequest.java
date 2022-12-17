package com.revature.ecommerce.entities.dtos.requests;

import com.revature.ecommerce.utils.utility_classes.PasswordHasher;

import java.util.Arrays;

public class NewPasswordRequest {

    private String email;
    private char[] oldPassword;
    private String newPassword1;
    private String newPassword2;
    private char[] hashedNewPassword;

    public NewPasswordRequest(String email, String oldPassword, String newPassword1, String newPassword2) {
        this.email = email;
        this.oldPassword = PasswordHasher.hash(oldPassword);
        this.newPassword1 = newPassword1;
        this.newPassword2 = newPassword2;
        this.hashedNewPassword = null;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public char[] getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(char[] oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword1() {
        return newPassword1;
    }

    public void setNewPassword1(String newPassword1) {
        this.newPassword1 = newPassword1;
    }

    public String getNewPassword2() {
        return newPassword2;
    }

    public void setNewPassword2(String newPassword2) {
        this.newPassword2 = newPassword2;
    }

    public char[] getHashedNewPassword() {
        return hashedNewPassword;
    }

    public void setHashedNewPassword(String hashedNewPassword) {
        this.hashedNewPassword = PasswordHasher.hash(hashedNewPassword);
    }

    @Override
    public String toString() {
        return "NewPasswordRequest{" +
                "email='" + email + '\'' +
                ", oldPassword=" + Arrays.toString(oldPassword) +
                ", newPassword1='" + newPassword1 + '\'' +
                ", newPassword2='" + newPassword2 + '\'' +
                ", hashedNewPassword=" + Arrays.toString(hashedNewPassword) +
                '}';
    }
}
