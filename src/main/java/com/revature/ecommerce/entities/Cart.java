package com.revature.ecommerce.entities;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;


// This is a junction table for users and items

@Entity
@Table(name = "carts")
public class Cart {
    
    @Column(name = "amount", nullable = false)
    private Integer amount;

    @ManyToOne
    @JoinColumn(
            name = "item_id",
            nullable = false
    )
    @JsonBackReference
    private Item item;


    @ManyToOne
    @JoinColumn(
            name = "user_id",
            nullable = false
    )
    @JsonBackReference
    private User user;


    public Cart() {
        super();
    }


    public Cart(Integer amount, Item item, User user) {
        this.amount = amount;
        this.item = item;
        this.user = user;
    }


    public Integer getAmount() {
        return amount;
    }


    public void setAmount(Integer amount) {
        this.amount = amount;
    }


    public Item getItem() {
        return item;
    }


    public void setItem(Item item) {
        this.item = item;
    }


    public User getUser() {
        return user;
    }


    public void setUser(User user) {
        this.user = user;
    }


    @Override
    public String toString() {
        return "Cart [amount=" + amount + ", item=" + item + ", user=" + user + "]";
    }


    


    
    
}
