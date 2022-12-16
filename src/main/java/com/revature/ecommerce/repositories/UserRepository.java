package com.revature.ecommerce.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.revature.ecommerce.entities.User;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, String> {

    @Query(value = "SELECT (email) FROM User")
    List<String> findAllEmails();

    @Query(value = "SELECT * FROM users WHERE email = ?1 AND password = ?2", nativeQuery = true)
    User findByEmailAndPassword(String email, char[] password);

    @Modifying
    @Query(value = "UPDATE User SET password = ?1 WHERE email = ?2 AND password = ?3")
    void updatePassword(char[] newPassword, String email, char[] oldPassword);
}
