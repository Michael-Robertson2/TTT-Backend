package com.revature.ecommerce.entities.dtos.requests;

public class NewCartRequest {

    private String userId;
    private String itemId;
    private int amount;

    public NewCartRequest() {
        super();
    }

    public NewCartRequest(String itemId, int amount) {
        this.itemId = itemId;
        this.amount = amount;
    }

    public NewCartRequest(String userId, String itemId) {
        this.userId = userId;
        this.itemId = itemId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "NewCartRequest{" +
                "userId='" + userId + '\'' +
                ", itemId='" + itemId + '\'' +
                ", amount=" + amount +
                '}';
    }
}
