package com.revature.ecommerce.entities.dtos.requests;


public class NewOrderRequest {
    private String shipping_id;
    private String user_id;

    public NewOrderRequest() {
        super();
    }

    


    public NewOrderRequest(String shipping_id, String user_id) {
        this.shipping_id = shipping_id;
        this.user_id = user_id;
    }



    public String getShipping_id() {
        return shipping_id;
    }

    public void setShipping_id(String shipping_id) {
        this.shipping_id = shipping_id;
    }

    

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "NewOrderRequest [shipping_id=" + shipping_id + ", user_id=" + user_id + "]";
    }

    

    
    
    
}
