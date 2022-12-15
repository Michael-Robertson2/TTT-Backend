package com.revature.ecommerce.repositories;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.revature.ecommerce.models.Item;

@Repository
public interface ItemRepository extends CrudRepository<Item, String>{
    
}
