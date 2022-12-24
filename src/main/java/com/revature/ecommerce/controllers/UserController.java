package com.revature.ecommerce.controllers;

import com.revature.ecommerce.entities.dtos.requests.NewInfoRequest;
import com.revature.ecommerce.entities.dtos.requests.NewPasswordRequest;
import com.revature.ecommerce.entities.dtos.requests.NewUserRequest;
import com.revature.ecommerce.entities.dtos.responses.Principal;
import com.revature.ecommerce.services.TokenService;
import com.revature.ecommerce.services.UserService;
import com.revature.ecommerce.utils.custom_exceptions.InvalidAuthException;
import com.revature.ecommerce.utils.custom_exceptions.InvalidUserException;
import com.revature.ecommerce.utils.utility_classes.PasswordHasher;
import org.aspectj.lang.annotation.Before;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final TokenService tokenService;

    public UserController(UserService userService, TokenService tokenService) {
        this.userService = userService;
        this.tokenService = tokenService;
    }

    @PostMapping
    public void signup(@RequestBody NewUserRequest req) {
        if (userService.passwordsMatch(req))
            if (userService.isValidPassword(req))
                if (userService.isValidEmail(req))
                    if (userService.isUniqueEmail(req)) {
                        req.setHashedPassword(PasswordHasher.hash(req.getPassword1()));
                        userService.signup(req);
                    }
    }

    @PutMapping("/password")
    public void editPassword(@RequestBody NewPasswordRequest req) {
        if (userService.isValidUser(req))
            if (userService.passwordsMatch(req))
                if (userService.isValidPassword(req)) {
                    req.setHashedNewPassword(req.getNewPassword1());
                    userService.updatePassword(req);
        }
    }

    @PutMapping
    public void updateInfo(@RequestBody NewInfoRequest req, HttpServletRequest hReq) {
        Principal principal = tokenService.isLoggedIn(hReq);
        req.setId(principal.getId());

        boolean sameEmail = false;
        if (req.getNewEmail() == null || req.getNewEmail().isEmpty())
            req.setNewEmail(principal.getEmail());
        if (req.getNewEmail().equals(principal.getEmail()))
            sameEmail = true;
        if (req.getNewGivenName() == null || req.getNewGivenName().isEmpty())
            req.setNewGivenName(principal.getGivenName());
        if (req.getNewSurname() == null || req.getNewSurname().isEmpty())
            req.setNewSurname(principal.getSurname());
        if (req.getNewCardNumber() == null || req.getNewCardNumber().isEmpty())
            req.setNewCardNumber(principal.getCardNumber());
        if (req.getNewExpirationDate() == null || req.getNewExpirationDate().isEmpty()) {
            Date expDate = principal.getExpirationDate();
            String dateStr;
            if (expDate != null) {
                DateFormat df = new SimpleDateFormat("yyyy-MM");
                dateStr = df.format(expDate);
            } else {
                dateStr = null;
            }
            req.setNewExpirationDate(dateStr);
        }

        if (userService.isValidEmail(req))
            if (sameEmail || userService.isUniqueEmail(req))
                if (userService.isValidCardNumber(req)) //Needs to be either 15 or 16 digits, no other length or type of character allowed.
                    if (userService.isValidExpDate(req)) //Needs to be in yyyy-MM format.
                        if (userService.cardAndDate(req))
                            userService.updateInfo(req);

    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidUserException.class)
    public InvalidUserException handleUserException(InvalidUserException e) {
        return e;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidAuthException.class)
    public InvalidAuthException handleAuthException(InvalidAuthException e) {
        return e;
    }
}
