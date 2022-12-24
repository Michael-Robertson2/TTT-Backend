package com.revature.ecommerce.repositories;

import com.revature.ecommerce.entities.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

@Repository
@Transactional
public interface ItemRepository extends CrudRepository<Item, String>{

    @Query(value = "SELECT * from items WHERE name = ?1", nativeQuery = true)
    List<Item> findAllByName(String name);


    // not sure if the query is written correctly
    @Query(value = "SELECT * from items ORDER BY (current_price) asc", nativeQuery = true)
    List<Item> findAllByPriceAsc();

    @Query(value = "SELECT * from items ORDER BY (current_price) desc", nativeQuery = true)
    List<Item> findAllByPriceDesc();


    @Modifying
    @Query(value = "DELETE from items WHERE id = ?1", nativeQuery = true)
    void delete(String id);

    @Modifying
    @Query(value = "UPDATE Item SET stock = ?1 WHERE id= ?2")
    void updateStock(int amount, String id);


}
