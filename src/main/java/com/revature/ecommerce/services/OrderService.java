package com.revature.ecommerce.services;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.revature.ecommerce.entities.enums.Status;
import com.revature.ecommerce.entities.dtos.requests.NewOrderRequest;
import org.springframework.stereotype.Service;
import com.revature.ecommerce.repositories.OrderRepository;

import javax.persistence.criteria.Order;

@Service
public class OrderService {
    private final OrderRepository orderRepository;



    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void createOrder(NewOrderRequest req) {

        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());


        orderRepository.save(UUID.randomUUID().toString(), timestamp, null, Status.Placed, req.getUser_id(), req.getShipping_id());
    }


    public List<Order> getAllItems() {
        return (List<Order>) orderRepository.findAll();
    }


    public List<Order> getAllByStatus(Status status) {
        return orderRepository.findAllByStatus(status);
    }


}
