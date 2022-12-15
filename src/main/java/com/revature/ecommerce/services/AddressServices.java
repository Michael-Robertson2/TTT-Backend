package com.revature.ecommerce.services;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.revature.ecommerce.dtos.requests.NewAddressRequest;
import com.revature.ecommerce.repositories.AddressRepository;
import com.revature.ecommerce.utils.custom_exceptions.InvalidAddressException;

@Service
public class AddressServices {
    private final AddressRepository addressRepository;

    public AddressServices(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public void createAddress(NewAddressRequest req) {
        addressRepository.save(UUID.randomUUID().toString(), req.getStreet(), req.getCity(), req.getState(), req.getZipcode(), req.getUser_id());
    }


    public boolean isValidStreet(NewAddressRequest req) {
        if (req.getStreet().isEmpty())
            throw new InvalidAddressException("Street name is required");
        return true;
    }

    public boolean isValidCity(NewAddressRequest req) {
        if (req.getCity().isEmpty())
            throw new InvalidAddressException("City name is required");
        return true;
    }

    public boolean isValidState(NewAddressRequest req) {
        if (req.getState().isEmpty())
            throw new InvalidAddressException("State name is required");
        return true;
    }

    public boolean isValidZipcode(NewAddressRequest req) {
        if (req.getZipcode().isEmpty())
            throw new InvalidAddressException("Zipcode name is required");
        return true;
    }
}
