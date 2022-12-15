package com.revature.ecommerce.repositories;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.revature.ecommerce.models.Order;
import com.revature.ecommerce.models.Status;

@Repository
public interface OrderRepository extends CrudRepository<Order, String> {
    
    @Query(value = "SELECT * from orders WHERE status = ?1", nativeQuery = true)
    List<Order> findAllByStatus(Status type);

    @Query(value = "SELECT * from orders WHERE user_id = ?1", nativeQuery = true)
    List<Order> findAllByUserId(String user_id);


    @Modifying
    @Query(value = "INSERT INTO addresses(id, purchase_date, delivery_date, status, user_id, shipping_id) VALUES (?1, ?2, ?3, ?4, ?5, ?6)", nativeQuery = true)
    void save(String id, Timestamp purchase_date, Timestamp delivery_date, Status status, String user_id, String shipping_id);
}
