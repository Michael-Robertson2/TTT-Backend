package com.revature.ecommerce.dtos.requests;

import com.revature.ecommerce.models.ItemType;

public class NewItemRequest {
    private String name;
    private Integer stock;
    private Double msrp;
    private Double currentPrice;
    private ItemType type;



    public NewItemRequest() {
        super();
    }



    public NewItemRequest(String name, Integer stock, Double msrp, Double currentPrice, ItemType type) {
        this.name = name;
        this.stock = stock;
        this.msrp = msrp;
        this.currentPrice = currentPrice;
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



    public void setStock(Integer stock) {
        this.stock = stock;
    }



    public Double getMsrp() {
        return msrp;
    }



    public void setMsrp(Double msrp) {
        this.msrp = msrp;
    }



    public Double getCurrentPrice() {
        return currentPrice;
    }



    public void setCurrentPrice(Double currentPrice) {
        this.currentPrice = currentPrice;
    }



    public ItemType getType() {
        return type;
    }



    public void setType(ItemType type) {
        this.type = type;
    }



    @Override
    public String toString() {
        return "NewItemRequest [name=" + name + ", stock=" + stock + ", msrp=" + msrp + ", currentPrice=" + currentPrice
                + ", type=" + type + "]";
    }


    



}
