package com.revature.ecommerce.entities.dtos.requests;

import com.revature.ecommerce.entities.User;

public class NewOrderRequest {

    private String addressId;
    private String userId;
    private String securityCode;
    private User user;

    public NewOrderRequest() {
        super();
    }

    public NewOrderRequest(String addressId, String securityCode) {
        this.addressId = addressId;
        this.securityCode = securityCode;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
