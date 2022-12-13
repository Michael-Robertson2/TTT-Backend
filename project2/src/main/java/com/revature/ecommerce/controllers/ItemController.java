package com.revature.ecommerce.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.revature.ecommerce.models.Item;
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
