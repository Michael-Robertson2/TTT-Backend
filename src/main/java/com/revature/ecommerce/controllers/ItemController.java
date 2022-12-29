package com.revature.ecommerce.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.revature.ecommerce.entities.dtos.responses.ItemPrincipal;
import com.revature.ecommerce.entities.dtos.enums.Role;
import com.revature.ecommerce.entities.dtos.requests.NewItemRequest;
import com.revature.ecommerce.entities.dtos.responses.Principal;

import org.springframework.web.bind.annotation.*;
import com.revature.ecommerce.services.ItemService;
import com.revature.ecommerce.services.TokenService;

import org.springframework.http.HttpStatus;

import com.revature.ecommerce.utils.custom_exceptions.InvalidAuthException;
import com.revature.ecommerce.utils.custom_exceptions.InvalidItemException;

@CrossOrigin
@RestController
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;
    private final TokenService tokenService;

    public ItemController(ItemService itemService, TokenService tokenService) {
        this.itemService = itemService;
        this.tokenService = tokenService;
    }

    @PostMapping
    public void createItem(@RequestBody NewItemRequest req, HttpServletRequest request) {
        String token = request.getHeader("authorization");
        if (token == null || token.isEmpty()) throw new InvalidAuthException("You are not signed in");

        Principal principal = tokenService.extractRequesterDetails(token);
        if (principal == null) throw new InvalidAuthException("Invalid token");
        if (!principal.getRole().equals(Role.Admin)) throw new InvalidAuthException("You are not authorized to do this");

        if (itemService.isValidName(req))
            if(itemService.isValidDescription(req))
                if (itemService.isValidstock(req))
                    if (itemService.isValidMsrp(req))
                        if (itemService.isValidCurrentPrice(req))
                            if(itemService.isValidType(req))
                                itemService.createItem(req);
    }

    @GetMapping("/all")
    public List<ItemPrincipal> getAllItems() {
        return itemService.getAllItems();
    }

    @GetMapping("/id")
        public ItemPrincipal getItemById(@RequestParam String id){
        return itemService.getById(id);
    }

    @GetMapping("/name")
    public List<ItemPrincipal> getAllItemsByName(@RequestParam String name){
        return itemService.getAllByName(name);
    }

    /*@GetMapping("/swapi")
    public void callSwapi() {
        String uri = "https://swapi.dev/api/";

    }*/

    @PutMapping("/update/id")
    public void updateAddress(@RequestBody NewItemRequest req, @RequestParam String id, HttpServletRequest request){
        String token = request.getHeader("authorization");
        if (token == null || token.isEmpty()) throw new InvalidAuthException("You are not signed in");
        
        Principal principal = tokenService.extractRequesterDetails(token);
        if (principal == null) throw new InvalidAuthException("Invalid token");
        if (!principal.getRole().equals(Role.Admin)) throw new InvalidAuthException("You are not authorized to do this");

        if (itemService.isValidName(req))
            if(itemService.isValidDescription(req))
                if (itemService.isValidstock(req))
                    if (itemService.isValidMsrp(req))
                        if (itemService.isValidCurrentPrice(req))
                            if(itemService.isValidType(req))
                                itemService.updateItem(req, id);

    }

    @DeleteMapping("/remove/id")
    public void removeItem(String id, HttpServletRequest request){
        String token = request.getHeader("authorization");
        if (token == null || token.isEmpty()) throw new InvalidAuthException("You are not signed in");

        Principal principal = tokenService.extractRequesterDetails(token);
        if (principal == null) throw new InvalidAuthException("Invalid token");
        if (!principal.getRole().equals(Role.Admin)) throw new InvalidAuthException("You are not authorized to do this");

        itemService.deleteItem(id);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidItemException.class)
    public InvalidItemException handleItemException(InvalidItemException e) {
        return e;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidAuthException.class)
    public InvalidAuthException handleAuthException(InvalidAuthException e) {
        return e;
    }
}
