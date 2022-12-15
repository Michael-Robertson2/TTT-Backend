package com.revature.ecommerce.repositories;


import com.revature.ecommerce.entities.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends CrudRepository<Item, String>{
    
}
