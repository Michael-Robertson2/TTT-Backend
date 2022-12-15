package com.revature.ecommerce.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    private String id;

    @Column(name = "purchase_date", nullable = false)
    private Timestamp purchaseDate;


    @Column(name = "delivery_date")
    private Timestamp deliveryDate;

    @Column(name="status", nullable = false)
    @Enumerated
    private Status status;


    @OneToMany(
            mappedBy = "cartOrder",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    @JsonManagedReference
    private List<Cart> cart;


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


    


    public Order() {
        super();
    }
    public Order(String id, Timestamp purchaseDate, Timestamp deliveryDate, Status status, User user, Address address) {
        this.id = id;
        this.purchaseDate = purchaseDate;
        this.deliveryDate = deliveryDate;
        this.status = status;
        this.user = user;
        this.address = address;
    }





    public Order(String id, Timestamp purchaseDate, Timestamp deliveryDate, Status status, List<Cart> cart, User user,
            Address address) {
        this.id = id;
        this.purchaseDate = purchaseDate;
        this.deliveryDate = deliveryDate;
        this.status = status;
        this.cart = cart;
        this.user = user;
        this.address = address;
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





    public List<Cart> getCart() {
        return cart;
    }





    public void setCart(List<Cart> cart) {
        this.cart = cart;
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





    @Override
    public String toString() {
        return "Order [id=" + id + ", purchaseDate=" + purchaseDate + ", deliveryDate=" + deliveryDate + ", status="
                + status + ", cart=" + cart + ", user=" + user + ", address=" + address + "]";
    }

}

