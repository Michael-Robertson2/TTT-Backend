package com.revature.ecommerce.controllers;

import com.revature.ecommerce.entities.dtos.requests.NewOrderRequest;
import com.revature.ecommerce.entities.dtos.responses.Principal;
import com.revature.ecommerce.services.TokenService;
import com.revature.ecommerce.utils.custom_exceptions.InvalidAddressException;
import com.revature.ecommerce.utils.custom_exceptions.InvalidAuthException;
import com.revature.ecommerce.utils.custom_exceptions.InvalidOrderException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.revature.ecommerce.services.OrderService;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin
@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    private final TokenService tokenService;

    public OrderController(OrderService orderService, TokenService tokenService) {
        this.orderService = orderService;
        this.tokenService = tokenService;
    }

    @PostMapping
    public void checkout(@RequestBody NewOrderRequest req, HttpServletRequest hReq) {
        Principal principal = tokenService.isLoggedIn(hReq);
        req.setUserId(principal.getId());

        if (orderService.isValidAddress(req))
            if (orderService.cartNotEmpty(req))
                if (orderService.validPayment(req))
                    orderService.createOrder(req);
    }

    @PutMapping
    public void cancelOrder(@RequestParam String orderId, HttpServletRequest req) {
        Principal principal = tokenService.isLoggedIn(req);

        if (orderService.isValidOrder(orderId))
            if (orderService.orderUserMatch(orderId, principal))
                if (orderService.isPending(orderId))
                    orderService.cancelOrder(orderId);

    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidAddressException.class)
    public InvalidAddressException handleAddressException (InvalidAddressException e) {
        return e;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidOrderException.class)
    public InvalidOrderException handleOrderException (InvalidOrderException e) {
        return e;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidAuthException.class)
    public InvalidAuthException handleAuthException (InvalidAuthException e) { return e; }
}
