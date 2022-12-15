package com.revature.ecommerce.dtos.requests;

public class NewAddressRequest {
    private String street;
    private String city;
    private String state;
    private String zipcode;
    private String user_id;


    public NewAddressRequest() {
        super();
    }


    public NewAddressRequest(String street, String city, String state, String zipcode, String user_id) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
        this.user_id = user_id;
    }


    public String getStreet() {
        return street;
    }


    public void setStreet(String street) {
        this.street = street;
    }


    public String getCity() {
        return city;
    }


    public void setCity(String city) {
        this.city = city;
    }


    public String getState() {
        return state;
    }


    public void setState(String state) {
        this.state = state;
    }


    public String getZipcode() {
        return zipcode;
    }


    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }



    public String getUser_id() {
        return user_id;
    }


    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }


    @Override
    public String toString() {
        return "NewAddressRequest [street=" + street + ", city=" + city + ", state=" + state + ", zipcode=" + zipcode
                + ", user_id=" + user_id + "]";
    }


    


    
    
}
