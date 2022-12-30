package com.revature.ecommerce.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.revature.ecommerce.entities.Address;

@Repository
@Transactional
public interface AddressRepository extends CrudRepository<Address, String> {

    @Modifying
    @Query(value = "INSERT INTO addresses(id, street, city, state, zipcode, user_id) VALUES (?1, ?2, ?3, ?4, ?5, ?6)", nativeQuery = true)
    void save(String id, String street, String city, String state, String zipcode, String user_id);

    @Modifying
    @Query(value = "UPDATE addresses SET street = ?1, city = ?2, state = ?3, zipcode = ?4 WHERE id =?5 AND user_id = ?6", nativeQuery = true)
    void update(String street, String city, String state, String zipcode, String id, String user_id);

    @Query(value = "SELECT * from addresses WHERE user_id = ?1", nativeQuery = true)
    List<Address> findAllByUserId(String name);

    @Modifying
    @Query(value = "DELETE from addresses WHERE id = ?1 AND user_id = ?2", nativeQuery = true)
    void delete(String id, String user_id);
}
