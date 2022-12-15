package com.revature.ecommerce.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.revature.ecommerce.models.Address;

@Repository
public interface AddressRepository extends CrudRepository<Address, String> {

    @Modifying
    @Query(value = "INSERT INTO addresses(id, street, city, state, zipcode, user_id) VALUES (?1, ?2, ?3, ?4, ?5, ?6)", nativeQuery = true)
    void save(String id, String street, String city, String state, String zipcode, String user_id);
    
}
