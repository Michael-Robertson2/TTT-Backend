package com.revature.ecommerce.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.ecommerce.dtos.requests.NewOrderRequest;
import com.revature.ecommerce.services.OrderService;

@CrossOrigin
@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    
    @PostMapping
    public void createorder(@RequestBody NewOrderRequest req){
        orderService.createOrder(req);
    }

}
