package com.revature.ecommerce.controllers;

import java.util.List;

import com.revature.ecommerce.entities.Item;
import com.revature.ecommerce.entities.enums.ItemType;
import com.revature.ecommerce.entities.dtos.requests.NewItemRequest;
import org.springframework.web.bind.annotation.*;
import com.revature.ecommerce.services.ItemService;
import org.springframework.http.HttpStatus;
import com.revature.ecommerce.utils.custom_exceptions.InvalidItemException;

@CrossOrigin
@RestController
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;

    

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }


    @PostMapping
    public void createItem(@RequestBody NewItemRequest req) {
        if (itemService.isValidName(req))
            if (itemService.isValidstock(req))
                if (itemService.isValidMsrp(req))
                    if (itemService.isValidCurrentPrice(req))
                        if(itemService.isValidType(req))
                            itemService.createItem(req);
    }

    
    @GetMapping("/all")
    public List<Item> getAllItems() {
        return itemService.getAllItems();
    }

    
    @GetMapping("/byType")
    public List<Item> getAllItemsByType(ItemType type){
        return itemService.getAllByType(type);
    }


    @GetMapping("/byName")
    public List<Item> getAllItemsByName(String name){
        return itemService.getAllByName(name);
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidItemException.class)
    public InvalidItemException handleUserException(InvalidItemException e) {
        return e;
    }
}
