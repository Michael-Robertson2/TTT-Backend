package com.revature.ecommerce.entities.dtos.responses;

public class CartPrincipal {

    private ItemPrincipal item;
    private int amount;

    public CartPrincipal(ItemPrincipal item, int amount) {
        this.item = item;
        this.amount = amount;
    }

    public ItemPrincipal getItem() {
        return item;
    }

    public void setItem(ItemPrincipal item) {
        this.item = item;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "CartPrincipal{" +
                "item=" + item +
                ", amount=" + amount +
                '}';
    }
}
