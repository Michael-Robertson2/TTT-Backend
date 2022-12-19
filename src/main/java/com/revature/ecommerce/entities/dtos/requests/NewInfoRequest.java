package com.revature.ecommerce.entities.dtos.requests;

public class NewInfoRequest {

    private String id;
    private String newEmail;
    private String newGivenName;
    private String newSurname;
    private String newCardNumber;
    private String newExpirationDate;

    public NewInfoRequest(String newEmail, String newGivenName, String newSurname, String newCardNumber, String newExpirationDate) {
        this.newEmail = newEmail;
        this.newGivenName = newGivenName;
        this.newSurname = newSurname;
        this.newCardNumber = newCardNumber;
        this.newExpirationDate = newExpirationDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNewEmail() {
        return newEmail;
    }

    public void setNewEmail(String newEmail) {
        this.newEmail = newEmail;
    }

    public String getNewGivenName() {
        return newGivenName;
    }

    public void setNewGivenName(String newGivenName) {
        this.newGivenName = newGivenName;
    }

    public String getNewSurname() {
        return newSurname;
    }

    public void setNewSurname(String newSurname) {
        this.newSurname = newSurname;
    }

    public String getNewCardNumber() {
        return newCardNumber;
    }

    public void setNewCardNumber(String newCardNumber) {
        this.newCardNumber = newCardNumber;
    }

    public String getNewExpirationDate() {
        return newExpirationDate;
    }

    public void setNewExpirationDate(String newExpirationDate) {
        this.newExpirationDate = newExpirationDate;
    }

    @Override
    public String toString() {
        return "NewInfoRequest{" +
                "newEmail='" + newEmail + '\'' +
                ", newGivenName='" + newGivenName + '\'' +
                ", newSurname='" + newSurname + '\'' +
                ", newCardNumber='" + newCardNumber + '\'' +
                ", newExpirationDate='" + newExpirationDate + '\'' +
                '}';
    }
}
