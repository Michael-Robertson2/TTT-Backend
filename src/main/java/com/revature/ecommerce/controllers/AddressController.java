package com.revature.ecommerce.controllers;

import com.revature.ecommerce.entities.Address;
import com.revature.ecommerce.entities.dtos.requests.NewAddressRequest;
import com.revature.ecommerce.entities.dtos.responses.Principal;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.HttpStatus;
import com.revature.ecommerce.services.AddressService;
import com.revature.ecommerce.services.TokenService;
import com.revature.ecommerce.utils.custom_exceptions.InvalidAddressException;
import com.revature.ecommerce.utils.custom_exceptions.InvalidAuthException;

@CrossOrigin
@RestController
@RequestMapping("/addresses")
public class AddressController {
    private final AddressService addressService;
    private final TokenService tokenService;

    public AddressController(AddressService addressService, TokenService tokenService) {
        this.addressService = addressService;
        this.tokenService = tokenService;
    }

    @PostMapping
    public void createAddress(@RequestBody NewAddressRequest req, HttpServletRequest request) {
        String token = request.getHeader("authorization");
        if (token == null || token.isEmpty()) throw new InvalidAuthException("You are not signed in");
        
        Principal principal = tokenService.extractRequesterDetails(token);
        if (principal == null) throw new InvalidAuthException("Invalid token");

        req.setUser_id(principal.getId());
        
        if (addressService.isValidStreet(req))
            if (addressService.isValidCity(req))
                if (addressService.isValidState(req))
                    if (addressService.isValidZipcode(req))
                        addressService.createAddress(req);
    }

    @PutMapping("/update/id")
    public void updateAddress(@RequestBody NewAddressRequest req, @RequestParam String id, HttpServletRequest request){
        String token = request.getHeader("authorization");
        if (token == null || token.isEmpty()) throw new InvalidAuthException("You are not signed in");
        
        Principal principal = tokenService.extractRequesterDetails(token);
        if (principal == null) throw new InvalidAuthException("Invalid token");

        if (addressService.isValidStreet(req))
            if (addressService.isValidCity(req))
                if (addressService.isValidState(req))
                    if (addressService.isValidZipcode(req))
                        addressService.updateAddress(req, id, principal.getId());

    }

    @GetMapping("/all/userId")
    public List<Address> getAllByUserId(HttpServletRequest request){
        String token = request.getHeader("authorization");
        if (token == null || token.isEmpty()) throw new InvalidAuthException("You are not signed in");
        
        Principal principal = tokenService.extractRequesterDetails(token);
        if (principal == null) throw new InvalidAuthException("Invalid token");
        String user_id = principal.getId();
        return addressService.getAllByUserId(user_id);
    }

    @DeleteMapping("/remove/id")
    public void deleteAddress(@RequestParam String id, HttpServletRequest request){
        String token = request.getHeader("authorization");
        if (token == null || token.isEmpty()) throw new InvalidAuthException("You are not signed in");
        
        Principal principal = tokenService.extractRequesterDetails(token);
        if (principal == null) throw new InvalidAuthException("Invalid token");
        String user_id = principal.getId();

        addressService.deleteAddress(id, user_id);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidAuthException.class)
    public InvalidAuthException handleAuthException(InvalidAuthException e) {
        return e;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidAddressException.class)
    public InvalidAddressException handleAddressException(InvalidAddressException e) {
        return e;
    }

}
