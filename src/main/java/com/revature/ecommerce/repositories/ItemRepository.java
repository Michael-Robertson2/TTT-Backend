package com.revature.ecommerce.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.revature.ecommerce.models.Item;
import com.revature.ecommerce.models.ItemType;

@Repository
public interface ItemRepository extends CrudRepository<Item, String>{
    
    @Query(value = "SELECT * from items WHERE type = ?1", nativeQuery = true)
    List<Item> findAllByType(ItemType type);
}
