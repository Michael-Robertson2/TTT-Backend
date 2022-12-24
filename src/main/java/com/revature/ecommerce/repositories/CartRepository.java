package com.revature.ecommerce.repositories;

import com.revature.ecommerce.entities.junctions.Cart;
import com.revature.ecommerce.entities.keys.CartKey;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends CrudRepository<Cart, CartKey> {

    @Modifying
    @Query(value = "UPDATE Cart SET amount= ?1 WHERE id= ?2")
    void updateAmount(int amount, CartKey id);

    @Query(value = "SELECT * FROM carts WHERE user_id= ?1", nativeQuery = true)
    List<Cart> findByUserId(String userId);

    @Modifying
    @Query(value = "DELETE FROM carts WHERE item_id= ?1 AND user_id= ?2", nativeQuery = true)
    void deleteByCompositeKey(String itemId, String userId);

    @Modifying
    @Query(value = "DELETE FROM carts WHERE user_id= ?1", nativeQuery = true)
    void deleteByUserId(String userId);
}
