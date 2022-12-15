package com.revature.ecommerce.repositories;

import com.revature.ecommerce.entities.Item;
import com.revature.ecommerce.entities.ItemType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface ItemRepository extends CrudRepository<Item, String>{
    
    @Query(value = "SELECT * from items WHERE type = ?1", nativeQuery = true)
    List<Item> findAllByType(ItemType type);
}
