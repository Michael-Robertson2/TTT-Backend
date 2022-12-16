package com.revature.ecommerce.entities.junctions;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.revature.ecommerce.entities.Item;
import com.revature.ecommerce.entities.Order;
import com.revature.ecommerce.entities.keys.OrdersAndItemsKey;

// This is a junction table for items and orders

@Entity
@Table(name = "OrdersAndItems")
public class OrdersAndItems {

    @EmbeddedId
    OrdersAndItemsKey id;
    
    @Column(name = "amount", nullable = false)
    private Integer amount;

    @Column(name = "purchase_price", nullable = false)
    private Double purchasePrice;

    @ManyToOne
    @MapsId("itemId")
    @JoinColumn(
            name = "item_id",
            nullable = false
    )
    @JsonBackReference
    private Item item;


    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(
            name = "order_id",
            nullable = false
    )
    @JsonBackReference
    private Order order;


    public OrdersAndItems() {
        super();
    }

    public OrdersAndItems(OrdersAndItemsKey id, Integer amount, Double purchasePrice, Item item, Order order) {
        this.id = id;
        this.amount = amount;
        this.purchasePrice = purchasePrice;
        this.item = item;
        this.order = order;
    }

    public OrdersAndItemsKey getId() {
        return id;
    }

    public void setId(OrdersAndItemsKey id) {
        this.id = id;
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

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(Double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }
    @Override
    public String toString() {
        return "OrdersAndItems{" +
                "id=" + id +
                ", item=" + item +
                ", order=" + order +
                ", amount=" + amount +
                ", purchasePrice=" + purchasePrice +
                '}';
    }
}
