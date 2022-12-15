package com.revature.ecommerce.services;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.revature.ecommerce.dtos.requests.NewOrderRequest;
import com.revature.ecommerce.models.Order;
import com.revature.ecommerce.models.Status;
import com.revature.ecommerce.repositories.OrderRepository;

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
