package com.revature.ecommerce.controllers;

import com.revature.ecommerce.entities.dtos.requests.NewAddressRequest;

import com.revature.ecommerce.entities.dtos.responses.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.HttpStatus;
import com.revature.ecommerce.services.AddressServices;
import com.revature.ecommerce.services.TokenService;
import com.revature.ecommerce.utils.custom_exceptions.InvalidAddressException;
import com.revature.ecommerce.utils.custom_exceptions.InvalidAuthException;

@CrossOrigin
@RestController
@RequestMapping("/addresses")
public class AddressController {
    private final AddressServices addressService;
    private final TokenService tokenService;

    
    
    public AddressController(AddressServices addressService, TokenService tokenService) {
        this.addressService = addressService;
        this.tokenService = tokenService;
    }



    @PostMapping
    public void createAddress(@RequestBody NewAddressRequest req, HttpServletRequest request) {
        String token = request.getHeader("authorization");
        if (token == null || token.isEmpty()) throw new InvalidAuthException("You are not signed in");

        Principal principal = tokenService.extractRequesterDetails(token);
        if (principal == null) throw new InvalidAuthException("Invalid token");

        if (addressService.isValidStreet(req))
            if (addressService.isValidCity(req))
                if (addressService.isValidState(req))
                    if (addressService.isValidZipcode(req))
                        addressService.createAddress(req);
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

    /*@PutMapping
    public void editAddress(@RequestBody NewAddressRequest req) {
        if
    }*/
}
