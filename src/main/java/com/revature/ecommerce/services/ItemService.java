package com.revature.ecommerce.services;

import java.util.List;
import java.util.UUID;

import com.revature.ecommerce.entities.Item;
import com.revature.ecommerce.entities.enums.ItemType;
import com.revature.ecommerce.entities.dtos.requests.NewItemRequest;
import org.springframework.stereotype.Service;
import com.revature.ecommerce.repositories.ItemRepository;
import com.revature.ecommerce.utils.custom_exceptions.InvalidItemException;

@Service
public class ItemService {

    private final ItemRepository itemRepository;
    
    

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public void createItem(NewItemRequest req) {
        Item createdItem = new Item(UUID.randomUUID().toString(), req.getName(), null, req.getStock(), req.getMsrp(), req.getCurrentPrice(), null, req.getType());
        itemRepository.save(createdItem);
    }



    public List<Item> getAllItems() {
        return (List<Item>) itemRepository.findAll();
    }


    public List<Item> getAllByType(ItemType type) {
        return itemRepository.findAllByType(type);
    }


    public boolean isValidName(NewItemRequest req) {
        if (req.getName().isEmpty())
            throw new InvalidItemException("Item name is required");
        return true;
    }

    public boolean isValidstock(NewItemRequest req) {
        if (req.getStock() < 0)
            throw new InvalidItemException("Enter a valid amount");
        return true;
    }

    public boolean isValidMsrp(NewItemRequest req) {
        if (req.getMsrp() < 0.00)
            throw new InvalidItemException("Enter a valid msrp");
        return true;
    }

    public boolean isValidCurrentPrice(NewItemRequest req) {
        if (req.getCurrentPrice() < 0.00)
            throw new InvalidItemException("Enter a valid price");
        return true;
    }


    public boolean isValidType(NewItemRequest req) {
        if (req.getType() == null)
            throw new InvalidItemException("Type is required");
        return true;
    }
}
