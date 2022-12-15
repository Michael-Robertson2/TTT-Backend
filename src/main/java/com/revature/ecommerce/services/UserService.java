package com.revature.ecommerce.services;

import com.revature.ecommerce.entities.dtos.requests.NewLoginRequest;
import com.revature.ecommerce.entities.dtos.requests.NewUserRequest;
import com.revature.ecommerce.entities.User;
import com.revature.ecommerce.entities.dtos.responses.Principal;
import com.revature.ecommerce.repositories.UserRepository;
import com.revature.ecommerce.utils.custom_exceptions.InvalidAuthException;
import com.revature.ecommerce.utils.custom_exceptions.InvalidUserException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepo;

    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public void signup(NewUserRequest req) {
        User createdUser = new User(UUID.randomUUID().toString(), req.getEmail(), req.getHashedPassword(), req.getGivenName(), req.getSurname());
        userRepo.save(createdUser);
    }

    public Principal login(NewLoginRequest req) {
        User validUser = userRepo.findByEmailAndPassword(req.getEmail(), req.getPassword());

        if (validUser == null) throw new InvalidAuthException("Invalid email or password");

        return new Principal(validUser.getEmail(), validUser.getGivenName(), validUser.getSurname(),
                    validUser.getRole(), validUser.getCardNumber(), validUser.getExpirationDate());
    }

    public boolean passwordsMatch(NewUserRequest req) {
        if (!req.getPassword1().equals(req.getPassword2()))
            throw new InvalidUserException("Passwords do not match");
        return true;
    }

    public boolean isValidPassword(NewUserRequest req) {
        if (!req.getPassword1().matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$"))
            throw new InvalidUserException("Invalid password, must contain at least eight characters, one letter, and one number");
        return true;
    }

    public boolean isValidEmail(NewUserRequest req) {
        if (!req.getEmail().matches("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*" +
                "|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")" +
                "@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.)" +
                "{3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\" +
                "[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])"))
            throw new InvalidUserException("Invalid email");
        return true; //I don't know if this will work, we'll have to see.
    }

    public boolean isUniqueEmail(NewUserRequest req) {
        List<String> emails = userRepo.findAllEmails();
        if (emails.contains(req.getEmail()))
            throw new InvalidUserException("An account is already associated with this email");
        return true;
    }
}
