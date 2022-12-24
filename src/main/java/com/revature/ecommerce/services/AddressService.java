package com.revature.ecommerce.services;

import java.util.List;
import java.util.UUID;

import com.revature.ecommerce.entities.Address;
import com.revature.ecommerce.entities.dtos.requests.NewAddressRequest;


import org.springframework.stereotype.Service;

import com.revature.ecommerce.repositories.AddressRepository;
import com.revature.ecommerce.utils.custom_exceptions.InvalidAddressException;


@Service
public class AddressService {
    private final AddressRepository addressRepository;


    

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }


    public void createAddress(NewAddressRequest req) {

        addressRepository.save(UUID.randomUUID().toString(), req.getStreet(), req.getCity(), req.getState(), req.getZipcode(), req.getUser_id());
    }


    public void updateAddress(NewAddressRequest req, String id, String user_id) {
        addressRepository.update(req.getStreet(), req.getCity(), req.getState(), req.getZipcode(), id, user_id);
    }


    public void deleteAddress(String id, String user_id) {
        addressRepository.delete(id, user_id);
    }


    public List<Address> getAllByUserId(String user_id){
        return addressRepository.findAllByUserId(user_id);
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
