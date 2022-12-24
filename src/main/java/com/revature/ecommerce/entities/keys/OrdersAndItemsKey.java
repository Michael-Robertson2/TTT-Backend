package com.revature.ecommerce.entities.keys;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class OrdersAndItemsKey implements Serializable{


    @Column(name = "item_id")
    String itemId;

    @Column(name = "order_id")
    String orderId;

    public OrdersAndItemsKey() {
        super();
    }

    public OrdersAndItemsKey(String itemId, String orderId) {
        this.itemId = itemId;
        this.orderId = orderId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrdersAndItemsKey ordersAndItemsKey = (OrdersAndItemsKey) o;
        return Objects.equals(itemId, ordersAndItemsKey.itemId) && Objects.equals(orderId, ordersAndItemsKey.orderId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemId, orderId);
    }
}
    

