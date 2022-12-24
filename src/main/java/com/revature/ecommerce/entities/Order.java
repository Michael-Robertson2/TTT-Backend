package com.revature.ecommerce.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.revature.ecommerce.entities.enums.Status;
import com.revature.ecommerce.entities.junctions.OrdersAndItems;

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
            mappedBy = "order",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    @JsonManagedReference(value="order-item-connection")
    private List<OrdersAndItems> orderItems;


    @ManyToOne
    @JoinColumn(
            name = "user_id",
            nullable = false
    )
    @JsonBackReference(value= "user-orders")
    private User user;

    @ManyToOne
    @JoinColumn(
            name = "shipping_id",
            nullable = false
    )
    @JsonBackReference(value = "address-orders")
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

    public Order(String id, Timestamp purchaseDate, Timestamp deliveryDate, Status status,
            List<OrdersAndItems> orderItems, User user, Address address) {
        this.id = id;
        this.purchaseDate = purchaseDate;
        this.deliveryDate = deliveryDate;
        this.status = status;
        this.orderItems = orderItems;
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
    
    public List<OrdersAndItems> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrdersAndItems> orderItems) {
        this.orderItems = orderItems;
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
                + status + ", orderItems=" + orderItems + ", user=" + user + ", address=" + address + "]";
    }
}

