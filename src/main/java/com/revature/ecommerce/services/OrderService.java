package com.revature.ecommerce.services;

import java.sql.Timestamp;
import java.util.*;

import com.revature.ecommerce.entities.Address;
import com.revature.ecommerce.entities.Item;
import com.revature.ecommerce.entities.Order;
import com.revature.ecommerce.entities.User;
import com.revature.ecommerce.entities.dtos.requests.NewAddressRequest;
import com.revature.ecommerce.entities.dtos.responses.Principal;
import com.revature.ecommerce.entities.enums.Status;
import com.revature.ecommerce.entities.dtos.requests.NewOrderRequest;
import com.revature.ecommerce.entities.junctions.Cart;
import com.revature.ecommerce.entities.junctions.OrdersAndItems;
import com.revature.ecommerce.entities.keys.OrdersAndItemsKey;
import com.revature.ecommerce.repositories.*;
import com.revature.ecommerce.utils.custom_exceptions.InvalidAddressException;
import com.revature.ecommerce.utils.custom_exceptions.InvalidOrderException;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
@Transactional
public class OrderService {
    private final OrderRepository orderRepo;
    private final UserRepository userRepo;
    private final AddressRepository addressRepo;
    private final ItemRepository itemRepo;
    private CartRepository cartRepo;

    public OrderService(OrderRepository orderRepo, UserRepository userRepo, AddressRepository addressRepo, ItemRepository itemRepo, CartRepository cartRepo) {
        this.orderRepo = orderRepo;
        this.userRepo = userRepo;
        this.addressRepo = addressRepo;
        this.itemRepo = itemRepo;
        this.cartRepo = cartRepo;
    }

    public void createOrder(NewOrderRequest req) {

        Date date = new Date(System.currentTimeMillis());
        Timestamp timestamp = new Timestamp(date.getTime());

        Optional<Address> address = addressRepo.findById(req.getAddressId());

        Order createdOrder = new Order(UUID.randomUUID().toString(), timestamp, null, Status.Placed, new ArrayList<>(), req.getUser(), address.get());
        List<Cart> cart = req.getUser().getCart();

        String orderId = createdOrder.getId();
        for (Cart c : cart) {
            String itemId = c.getId().getItemId();
            OrdersAndItemsKey key = new OrdersAndItemsKey(itemId, orderId);
            int amount = c.getAmount();
            Item item = c.getItem();
            double price = amount * item.getCurrent_price();

            createdOrder.getOrderItems().add(new OrdersAndItems(key, amount, price, item, createdOrder));

            item.setStock(item.getStock() - amount);
            itemRepo.updateStock(item.getStock(), itemId);
        }
        cartRepo.deleteByUserId(req.getUserId());
        orderRepo.save(createdOrder);
    }

    public void cancelOrder(String id) {
        orderRepo.setStatus(Status.Canceled.ordinal(), id);
    }

    public List<Order> getAllItems() {
        return (List<Order>) orderRepo.findAll();
    }

    public List<Order> getAllByStatus(Status status) {
        return orderRepo.findAllByStatus(status);
    }

    public boolean isValidAddress(NewOrderRequest req) {
        Optional<Address> address = addressRepo.findById(req.getAddressId());
        if (!address.isPresent())
            throw new InvalidAddressException("Invalid Address Id");

        Optional<User> user = userRepo.findById(req.getUserId());
        if (!user.get().getAddresses().contains(address.get()))
            throw new InvalidAddressException("Address is not associated with this account");
        req.setUser(user.get());
        return true;
    }

    public boolean cartNotEmpty(NewOrderRequest req) {
        if(req.getUser().getCart().isEmpty())
            throw new InvalidOrderException("Your cart is empty");
        return true;
    }

    public boolean validPayment(NewOrderRequest req) {
        if (req.getUser().getCardNumber() == null || req.getUser().getExpirationDate() == null || !isValidSecurityCode(req.getSecurityCode()))
            throw new InvalidOrderException("Invalid Payment credentials");
        return true;
    }

    public boolean isValidOrder(String id) {
        Optional<Order> order = orderRepo.findById(id);
        if (!order.isPresent())
            throw new InvalidOrderException("Invalid Order ID");
        return true;
    }

    public boolean orderUserMatch(String id, Principal principal) {
        Optional<Order> order = orderRepo.findById(id);
        if (!order.get().getUser().getId().equals(principal.getId()))
            throw new InvalidOrderException("Order is not associated with this account");
        return true;
    }

    public boolean isPending(String id) {
        Optional<Order> order = orderRepo.findById(id);

        if (!order.get().getStatus().equals(Status.Placed))
            throw new InvalidOrderException("Order cannot be cancelled at this time.");
        return true;
    }

    private boolean isValidSecurityCode(String code) {
        if (code == null)
            return false;
        if (code.length() != 3)
            return false;
        for (int i = 0; i < 3; i++)
            if (!Character.isDigit(code.charAt(i)))
                return false;
        return true;
    }
}
