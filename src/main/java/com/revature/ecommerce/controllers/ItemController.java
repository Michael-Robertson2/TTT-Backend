package com.revature.ecommerce.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.revature.ecommerce.dtos.requests.NewItemRequest;
import com.revature.ecommerce.models.Item;
import com.revature.ecommerce.models.ItemType;
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
    public void signup(@RequestBody NewItemRequest req) {
        if (itemService.isValidName(req))
            if (itemService.isValidstock(req))
                if (itemService.isValidMsrp(req))
                    if (itemService.isValidCurrentPrice(req))
                        if(itemService.isValidType(req))
                            itemService.createItem(req);
    }

    
    @GetMapping
    public List<Item> getAllItems() {
        return itemService.getAllItems();
    }

    
    @GetMapping
    public List<Item> getAllItemsByType(ItemType type){
        return itemService.getAllByType(type);
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidItemException.class)
    public InvalidItemException handleUserException(InvalidItemException e) {
        return e;
    }
}
