package com.revature.ecommerce.models;

import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
public class Item {
    @Id
    private String id;


    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;


    @Column(name = "stock", nullable = false)
    private Integer stock;

    @Column(name = "msrp", nullable = false)
    private Double msrp;


    @Column(name = "current_price", nullable = false)
    private Double current_price;

    @Column(name = "img_url")
    private String img_url;



    @ManyToOne
    @JoinColumn(
            name = "item_type_id",
            nullable = false
    )
    @JsonBackReference
    private ItemType itemType;



    @ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name = "order_items",
			joinColumns = @JoinColumn(name = "item_id"),
			inverseJoinColumns = @JoinColumn(name = "order_id")
	)
    private List<Order> orders;



    @ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name = "cart",
			joinColumns = @JoinColumn(name = "item_id"),
			inverseJoinColumns = @JoinColumn(name = "user_id")
	)
    private List<User> users;



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


    public List<Order> getOrders() {
        return orders;
    }


    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }


    public List<User> getUsers() {
        return users;
    }


    public void setUsers(List<User> users) {
        this.users = users;
    }



    @Override
    public String toString() {
        return "Item [id=" + id + ", name=" + name + ", description=" + description + ", stock=" + stock + ", msrp="
                + msrp + ", current_price=" + current_price + ", img_url=" + img_url + ", itemType=" + itemType + "]";
    }


    


    
}
