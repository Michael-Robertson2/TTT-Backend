package com.revature.ecommerce.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.revature.ecommerce.entities.dtos.enums.ItemType;
import com.revature.ecommerce.entities.junctions.Cart;
import com.revature.ecommerce.entities.junctions.OrdersAndItems;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "items")
public class Item {
    @Id
    private String id;


    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable= false)
    private String description;


    @Column(name = "stock", nullable = false)
    private Integer stock;

    @Column(name = "msrp", nullable = false)
    private Double msrp;


    @Column(name = "current_price", nullable = false)
    private Double current_price;

    @Column(name = "img_url")
    private String img_url;

    @Column(name="item_type_id", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private ItemType itemType;

    @OneToMany(
            mappedBy = "item",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    @JsonManagedReference(value="item-in-cart")
    private List<Cart> carts;

    @OneToMany(
            mappedBy = "item",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    @JsonManagedReference(value="item-order-connection")
    private List<OrdersAndItems> itemOrders;


    public Item() {
        super();
    }

    



    public Item(String id, String name, String description, Integer stock, Double msrp, Double current_price,
            String img_url, ItemType itemType) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.stock = stock;
        this.msrp = msrp;
        this.current_price = current_price;
        this.img_url = img_url;
        this.itemType = itemType;
    }





    public Item(String id, String name, String description, Integer stock, Double msrp, Double current_price,
            String img_url, ItemType itemType, List<Cart> carts, List<OrdersAndItems> itemOrders) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.stock = stock;
        this.msrp = msrp;
        this.current_price = current_price;
        this.img_url = img_url;
        this.itemType = itemType;
        this.carts = carts;
        this.itemOrders = itemOrders;
    }



    public String getId() {
        return id;
    }



    public void setId(String id) {
        this.id = id;
    }



    public String getName() {
        return name;
    }



    public void setName(String name) {
        this.name = name;
    }



    public String getDescription() {
        return description;
    }



    public void setDescription(String description) {
        this.description = description;
    }



    public Integer getStock() {
        return stock;
    }



    public void setStock(Integer stock) {
        this.stock = stock;
    }



    public Double getMsrp() {
        return msrp;
    }



    public void setMsrp(Double msrp) {
        this.msrp = msrp;
    }



    public Double getCurrent_price() {
        return current_price;
    }



    public void setCurrent_price(Double current_price) {
        this.current_price = current_price;
    }



    public String getImg_url() {
        return img_url;
    }



    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }



    public ItemType getItemType() {
        return itemType;
    }



    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }


    public List<Cart> getCarts() {
        return carts;
    }



    public void setCart(List<Cart> carts) {
        this.carts = carts;
    }




    public List<OrdersAndItems> getItemOrders() {
        return itemOrders;
    }



    public void setItemOrders(List<OrdersAndItems> itemOrders) {
        this.itemOrders = itemOrders;
    }

    public void setCarts(List<Cart> carts) {
        this.carts = carts;
    }

    @Override
    public String toString() {
        return "Item [id=" + id + ", name=" + name + ", description=" + description + ", stock=" + stock + ", msrp="
                + msrp + ", current_price=" + current_price + ", img_url=" + img_url + ", itemType=" + itemType
                + ", carts=" + carts + ", itemOrders=" + itemOrders + "]";
    }
}
