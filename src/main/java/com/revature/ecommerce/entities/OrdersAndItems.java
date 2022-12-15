package com.revature.ecommerce.models;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;


// This is a junction table for items and orders

@Entity
@Table(name = "OrdersAndItems")
public class OrdersAndItems {
    
    @Column(name = "amount", nullable = false)
    private Integer amount;

    @Column(name = "purchase_price", nullable = false)
    private Double purchasePrice;


    @ManyToOne
    @JoinColumn(
            name = "item_id",
            nullable = false
    )
    @JsonBackReference
    private Item item;


    @ManyToOne
    @JoinColumn(
            name = "order_id",
            nullable = false
    )
    @JsonBackReference
    private Order order;


    public OrdersAndItems() {
        super();
    }


    public OrdersAndItems(Integer amount, Double purchasePrice, Item item, Order order) {
        this.amount = amount;
        this.purchasePrice = purchasePrice;
        this.item = item;
        this.order = order;
    }


    public Integer getAmount() {
        return amount;
    }


    public void setAmount(Integer amount) {
        this.amount = amount;
    }


    public Double getPurchasePrice() {
        return purchasePrice;
    }


    public void setPurchasePrice(Double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }


    public Item getItem() {
        return item;
    }


    public void setItem(Item item) {
        this.item = item;
    }


    public Order getOrder() {
        return order;
    }


    public void setOrder(Order order) {
        this.order = order;
    }


    @Override
    public String toString() {
        return "OrdersAndItems [amount=" + amount + ", purchasePrice=" + purchasePrice + ", item=" + item + ", order="
                + order + "]";
    }


    
    
}
