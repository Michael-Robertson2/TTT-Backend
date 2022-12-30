package com.revature.ecommerce.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import com.revature.ecommerce.entities.Item;
import com.revature.ecommerce.entities.dtos.requests.NewItemRequest;
import com.revature.ecommerce.entities.dtos.responses.ItemPrincipal;
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
        Item createdItem = new Item(UUID.randomUUID().toString(), req.getName(), req.getDescription(), req.getStock(), req.getMsrp(), req.getCurrent_price(), null, req.getType());
        itemRepository.save(createdItem);
    }

    public List<ItemPrincipal> getAllItems() {
        return processItems((List<Item>) itemRepository.findAll());
    }

    public List<Item> getAllItemsByPriceAsc() {
        return (List<Item>) itemRepository.findAllByPriceAsc();
    }

    public List<Item> getAllItemsByPriceDesc() {
        return (List<Item>) itemRepository.findAllByPriceDesc();
    }

    public ItemPrincipal getById(String id) {

        Optional<Item> opt= itemRepository.findById(id);
        if (!opt.isPresent())
            return null;

        Item item = opt.get();
        return new ItemPrincipal(item.getId(), item.getName(), item.getDescription(), item.getStock(), item.getMsrp(), item.getCurrent_price(), item.getImg_url(), item.getItemType());
    }

    public List<ItemPrincipal> getAllByName(String name) {
        return processItems(itemRepository.findAllByName(name));
    }

    public void updateItem(NewItemRequest req, String id) {
        Item itemInDB = itemRepository.findById(id).get();
        itemInDB.setName(req.getName());
        itemInDB.setDescription(req.getDescription());
        itemInDB.setStock(req.getStock());
        itemInDB.setMsrp(req.getMsrp());
        itemInDB.setCurrent_price(req.getCurrent_price());
        itemInDB.setItemType(req.getType());
        itemRepository.save(itemInDB);
    }

    public void deleteItem(String id){
        itemRepository.delete(id);
    }


    public boolean isValidName(NewItemRequest req) {
        if (req.getName().isEmpty())
            throw new InvalidItemException("Item name is required");
        return true;
    }

    public boolean isValidDescription(NewItemRequest req) {
        if (req.getDescription().isEmpty())
            throw new InvalidItemException("Item description is required");
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
        if (req.getCurrent_price() < 0.00)
            throw new InvalidItemException("Enter a valid price");
        return true;
    }

    public boolean isValidType(NewItemRequest req) {
        if (req.getType() == null)
            throw new InvalidItemException("Type is required");
        return true;
    }

    private List<ItemPrincipal> processItems (List<Item> list) {
        List<ItemPrincipal> items = new ArrayList<>();
        for (Item i: list) {
            items.add(new ItemPrincipal(i.getId(), i.getName(), i.getDescription(), i.getStock(), i.getMsrp(), i.getCurrent_price(), i.getImg_url(), i.getItemType()));
        }
        return items;
    }
}
