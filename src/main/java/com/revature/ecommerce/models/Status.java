package com.revature.ecommerce.models;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Status {
    @Id
    private String id;

    @Column(name = "status")
    private String status;


    @OneToMany(
            mappedBy = "orders",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    @JsonManagedReference
    private List<Order> orders;


    



    public Status() {
        super();
    }






    public Status(String id, String status, List<Order> orders) {
        this.id = id;
        this.status = status;
        this.orders = orders;
    }






    public String getId() {
        return id;
    }






    public void setId(String id) {
        this.id = id;
    }






    public String getStatus() {
        return status;
    }






    public void setStatus(String status) {
        this.status = status;
    }






    public List<Order> getOrders() {
        return orders;
    }






    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }






    @Override
    public String toString() {
        return "Status [id=" + id + ", status=" + status + ", orders=" + orders + "]";
    }


    


    
}
