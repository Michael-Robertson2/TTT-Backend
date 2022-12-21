package com.revature.ecommerce.entities.dtos.requests;


import com.revature.ecommerce.entities.enums.ItemType;

public class NewItemRequest {
    private String name;
    private String description;
    private Integer stock;
    private Double msrp;
    private Double current_price;
    private ItemType type;



    public NewItemRequest() {
        super();
    }



    



    public NewItemRequest(String name, String description, Integer stock, Double msrp, Double current_price,
            ItemType type) {
        this.name = name;
        this.description = description;
        this.stock = stock;
        this.msrp = msrp;
        this.current_price = current_price;
        this.type = type;
    }



    public String getName() {
        return name;
    }



    public void setName(String name) {
        this.name = name;
    }



    public Integer getStock() {
        return stock;
    }

    public String getDescription() {
        return description;
    }



    public void setDescription(String description) {
        this.description = description;
    }

    
    public void setStock(Integer stock) {
        this.stock = stock;
    }



    public Double getMsrp() {
        return msrp;
    }



    public void setMsrp(Double msrp) {
        this.msrp = msrp;
    }



    public Double getCurrent_price() {
        return current_price;
    }



    public void setCurrent_price(Double current_price) {
        this.current_price = current_price;
    }



    public ItemType getType() {
        return type;
    }



    public void setType(ItemType type) {
        this.type = type;
    }







    @Override
    public String toString() {
        return "NewItemRequest [name=" + name + ", description=" + description + ", stock=" + stock + ", msrp=" + msrp
                + ", current_price=" + current_price + ", type=" + type + "]";
    }



    









    



}
