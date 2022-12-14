package com.revature.ecommerce.models;

import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class ItemType {
    @Id
    private String id;

    @Column(name = "type")
    private String type;


    @OneToMany(
            mappedBy = "restaurant",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    @JsonManagedReference
    private List<Item> items;




    public ItemType() {
        super();
    }


    public ItemType(String id, String type, List<Item> items) {
        this.id = id;
        this.type = type;
        this.items = items;
    }


    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
    }

    

    public String getType() {
        return type;
    }


    public void setType(String type) {
        this.type = type;
    }


    public List<Item> getItems() {
        return items;
    }


    public void setItems(List<Item> items) {
        this.items = items;
    }



    @Override
    public String toString() {
        return "ItemType [id=" + id + ", type=" + type + ", items=" + items + "]";
    }
    
}
