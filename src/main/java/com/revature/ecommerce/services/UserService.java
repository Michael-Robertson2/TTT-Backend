package com.revature.ecommerce.services;


import com.revature.ecommerce.entities.dtos.requests.NewInfoRequest;
import com.revature.ecommerce.entities.dtos.requests.NewLoginRequest;
import com.revature.ecommerce.entities.dtos.requests.NewPasswordRequest;
import com.revature.ecommerce.entities.dtos.requests.NewUserRequest;
import com.revature.ecommerce.entities.User;
import com.revature.ecommerce.entities.dtos.responses.Principal;
import com.revature.ecommerce.repositories.UserRepository;
import com.revature.ecommerce.utils.custom_exceptions.InvalidAuthException;
import com.revature.ecommerce.utils.custom_exceptions.InvalidUserException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
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

        return new Principal(validUser.getId(), validUser.getEmail(), validUser.getGivenName(), validUser.getSurname(),
                    validUser.getRole(), validUser.getCardNumber(), validUser.getExpirationDate());
    }

    public void updatePassword(NewPasswordRequest req) {
        userRepo.updatePassword(req.getHashedNewPassword(), req.getEmail(), req.getOldPassword());
    }

    public void updateInfo(NewInfoRequest req) {
        userRepo.updateInfo(req.getNewEmail(), req.getNewGivenName(), req.getNewSurname(), req.getNewCardNumber(), toDate(req.getNewExpirationDate()), req.getId());
    }

    public boolean passwordsMatch(NewUserRequest req) {
        if (!passwordsMatch(req.getPassword1(), req.getPassword2()))
            throw new InvalidUserException("Passwords do not match");
        return true;
    }

    public boolean passwordsMatch(NewPasswordRequest req) {
        if (!passwordsMatch(req.getNewPassword1(), req.getNewPassword2()))
            throw new InvalidUserException("Passwords do not match");
        return true;
    }

    public boolean isValidPassword(NewUserRequest req) {
        if ((!isValidPassword(req.getPassword1())))
            throw new InvalidUserException("Invalid password, must contain at least eight characters, one letter, and one number");
        return true;
    }

    public boolean isValidPassword(NewPasswordRequest req) {
        if (!isValidPassword(req.getNewPassword1()))
            throw new InvalidUserException("Invalid password, must contain at least eight characters, one letter, and one number");
        return true;
    }

    public boolean isValidEmail(NewUserRequest req) {
        if (!isValidEmail(req.getEmail()))
            throw new InvalidUserException("Invalid email");
        return true;
    }

    public boolean isValidEmail(NewInfoRequest req) {
        if (!isValidEmail(req.getNewEmail()))
            throw new InvalidUserException("Invalid email");
        return true;
    }

    public boolean isUniqueEmail(NewUserRequest req) {
        if (!isUniqueEmail(req.getEmail()))
            throw new InvalidUserException("There is already an account associated with this email");
        return true;
    }

    public boolean isUniqueEmail(NewInfoRequest req) {
        if (!isUniqueEmail(req.getNewEmail()))
            throw new InvalidUserException("There is already an account associated with this email");
        return true;
    }

    public boolean isValidCardNumber(NewInfoRequest req) {
        if (!isValidCardNumber(req.getNewCardNumber()))
            throw new InvalidUserException("Invalid card number");
        return true;
    }

    public boolean isValidExpDate(NewInfoRequest req) {
        if (!isValidDate(req.getNewExpirationDate()))
            throw new InvalidUserException("Invalid expiration date");
        return true;
    }

    public boolean cardAndDate(NewInfoRequest req) {
        if (req.getNewCardNumber() != null && req.getNewExpirationDate() == null)
            throw new InvalidUserException("Expiration date required");
        if ((req.getNewCardNumber() == null && req.getNewExpirationDate() != null))
            throw new InvalidUserException("Card number required");
        return true;
    }

    public boolean isValidUser(NewPasswordRequest req) {
        User existingUser = userRepo.findByEmailAndPassword(req.getEmail(), req.getOldPassword());

        if (existingUser == null) throw new InvalidAuthException("Invalid email or old password");

        return true;
    }

    private boolean passwordsMatch(String password1, String password2) {
        return password1.equals(password2);
    }

    private boolean isValidPassword(String password) {
        return password.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$");
    }

    private boolean isValidEmail(String email) {
        return email.matches("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*" +
                "|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")" +
                "@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9]" +
                "[0-9]|[1-9]?[0-9]))\\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\" +
                "x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])");
    }

    private boolean isUniqueEmail(String email) {
        List<String> emails = userRepo.findAllEmails();
        return !emails.contains(email);
    }

    private boolean isValidDate(String date) {
        if (date == null) return true;
        return date.matches("^((?:19|20)[0-9][0-9])-(0?[1-9]|1[012])$");
    }

    private boolean isValidCardNumber(String num) {
        if (num == null) return true;

        int len = num.length();
        if (len != 16 && len != 15)
            return false;

        for (int i = 0; i < len; i++)
            if (!Character.isDigit(num.charAt(i)))
                return false;
        return true;
    }

    private Date toDate(String date) {
        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM");
            return df.parse(date);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
