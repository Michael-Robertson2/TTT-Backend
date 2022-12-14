package com.revature.ecommerce.models;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Order {
    @Id
    private String id;

    @Column(name = "purchase_date", nullable = false)
    private Timestamp purchaseDate;


    @Column(name = "delivery_date")
    private Timestamp deliveryDate;

    @ManyToOne
    @JoinColumn(
            name = "status_id",
            nullable = false
    )
    @JsonBackReference
    private Status status;

    @ManyToOne
    @JoinColumn(
            name = "user_id",
            nullable = false
    )
    @JsonBackReference
    private User user;


    @ManyToOne
    @JoinColumn(
            name = "shipping_id",
            nullable = false
    )
    @JsonBackReference
    private Address address;


    @ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name = "order_items",
			joinColumns = @JoinColumn(name = "order_id"),
			inverseJoinColumns = @JoinColumn(name = "item_id")
	)
    private List<Item> items;


    public Order() {
        super();
    }


    public Order(String id, Timestamp purchaseDate, Timestamp deliveryDate, Status status, User user, Address address,
            List<Item> items) {
        this.id = id;
        this.purchaseDate = purchaseDate;
        this.deliveryDate = deliveryDate;
        this.status = status;
        this.user = user;
        this.address = address;
        this.items = items;
    }


    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
    }


    public Timestamp getPurchaseDate() {
        return purchaseDate;
    }


    public void setPurchaseDate(Timestamp purchaseDate) {
        this.purchaseDate = purchaseDate;
    }


    public Timestamp getDeliveryDate() {
        return deliveryDate;
    }


    public void setDeliveryDate(Timestamp deliveryDate) {
        this.deliveryDate = deliveryDate;
    }


    public Status getStatus() {
        return status;
    }


    public void setStatus(Status status) {
        this.status = status;
    }


    public User getUser() {
        return user;
    }


    public void setUser(User user) {
        this.user = user;
    }


    public Address getAddress() {
        return address;
    }


    public void setAddress(Address address) {
        this.address = address;
    }


    public List<Item> getItems() {
        return items;
    }


    public void setItems(List<Item> items) {
        this.items = items;
    }


    @Override
    public String toString() {
        return "Order [id=" + id + ", purchaseDate=" + purchaseDate + ", deliveryDate=" + deliveryDate + ", status="
                + status + ", user=" + user + ", address=" + address + ", items=" + items + "]";
    }


    



    
    
}
