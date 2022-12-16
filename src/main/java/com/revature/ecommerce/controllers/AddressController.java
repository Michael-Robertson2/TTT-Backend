package com.revature.ecommerce.controllers;

import com.revature.ecommerce.entities.dtos.requests.NewAddressRequest;
import org.springframework.web.bind.annotation.*;


import com.revature.ecommerce.services.AddressServices;

@CrossOrigin
@RestController
@RequestMapping("/addresses")
public class AddressController {
    private final AddressServices addressService;

    public AddressController(AddressServices addressService) {
        this.addressService = addressService;
    }
    
    @PostMapping
    public void createAddress(@RequestBody NewAddressRequest req) {
        if (addressService.isValidStreet(req))
            if (addressService.isValidCity(req))
                if (addressService.isValidState(req))
                    if (addressService.isValidZipcode(req))
                        addressService.createAddress(req);
    }

    /*@PutMapping
    public void editAddress(@RequestBody NewAddressRequest req) {
        if
    }*/
}
