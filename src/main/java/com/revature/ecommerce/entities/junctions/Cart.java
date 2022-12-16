package com.revature.ecommerce.entities.junctions;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.revature.ecommerce.entities.Item;
import com.revature.ecommerce.entities.User;
import com.revature.ecommerce.entities.keys.CartKey;


// This is a junction table for users and items

@Entity
@Table(name = "carts")
public class Cart {

    @EmbeddedId
    CartKey id;

    @Column(name = "amount", nullable = false)
    private Integer amount;

    @ManyToOne
    @MapsId("itemId")
    @JoinColumn(
            name = "item_id",
            nullable = false
    )
    @JsonBackReference
    private Item item;


    @ManyToOne
    @MapsId("userId")
    @JoinColumn(
            name = "user_id",
            nullable = false
    )
    @JsonBackReference
    private User user;


    public Cart() {
        super();
    }

    public Cart(CartKey id, Item item, User user, Integer amount) {
        this.id = id;
        this.item = item;
        this.user = user;
        this.amount = amount;
    }


    public Cart(CartKey id, Integer amount, Item item, User user) {
        this.id = id;
        this.amount = amount;
        this.item = item;
        this.user = user;
    }

    public CartKey getId() {
        return id;
    }

    public void setId(CartKey id) {
        this.id = id;
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

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }


    @Override
    public String toString() {
        return "Cart [id=" + id + ", amount=" + amount + ", item=" + item + ", user=" + user + "]";
    }
}

