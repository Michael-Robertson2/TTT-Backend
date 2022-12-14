package com.revature.ecommerce.repositories;

import com.revature.ecommerce.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, String> {

    @Query(value = "SELECT (email) FROM User")
    List<String> findAllEmails();
}
