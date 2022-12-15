package com.revature.ecommerce.services;

import java.util.List;

import com.revature.ecommerce.entities.Item;
import org.springframework.stereotype.Service;

import com.revature.ecommerce.repositories.ItemRepository;

@Service
public class ItemService {

    private final ItemRepository itemRepository;
    
    

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }



    public List<Item> getAllItems() {
        return (List<Item>) itemRepository.findAll();
    }
}
