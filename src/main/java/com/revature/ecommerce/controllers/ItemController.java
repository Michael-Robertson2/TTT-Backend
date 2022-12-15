package com.revature.ecommerce.controllers;

import java.util.List;

import com.revature.ecommerce.entities.Item;
import org.springframework.web.bind.annotation.*;
import com.revature.ecommerce.services.ItemService;

@CrossOrigin
@RestController
public class ItemController {

    private final ItemService itemService;

    

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }



    @GetMapping
    public List<Item> getAllItems() {
        return itemService.getAllItems();
    }
    
}
