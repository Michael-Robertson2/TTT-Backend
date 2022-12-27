package com.revature.ecommerce.entities.dtos.responses;

import com.revature.ecommerce.entities.enums.ItemType;

public class ItemPrincipal {
    private String id;
    private String description;
    private double msrp;
    private double currentPrice;
    private String imgUrl;
    private ItemType itemType;

    public ItemPrincipal(String id, String description, double msrp, double currentPrice, String imgUrl, ItemType itemType) {
        this.id = id;
        this.description = description;
        this.msrp = msrp;
        this.currentPrice = currentPrice;
        this.imgUrl = imgUrl;
        this.itemType = itemType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getMsrp() {
        return msrp;
    }

    public void setMsrp(double msrp) {
        this.msrp = msrp;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }

    @Override
    public String toString() {
        return "ItemPrincipal{" +
                "id='" + id + '\'' +
                ", description='" + description + '\'' +
                ", msrp=" + msrp +
                ", currentPrice=" + currentPrice +
                ", imgUrl='" + imgUrl + '\'' +
                ", itemType=" + itemType +
                '}';
    }
}
