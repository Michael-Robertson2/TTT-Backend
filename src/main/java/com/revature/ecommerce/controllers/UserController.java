package com.revature.ecommerce.controllers;

import com.revature.ecommerce.entities.dtos.requests.NewPasswordRequest;
import com.revature.ecommerce.entities.dtos.requests.NewUserRequest;
import com.revature.ecommerce.services.UserService;
import com.revature.ecommerce.utils.custom_exceptions.InvalidAuthException;
import com.revature.ecommerce.utils.custom_exceptions.InvalidUserException;
import com.revature.ecommerce.utils.utility_classes.PasswordHasher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
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

    @PutMapping
    public void editPassword(@RequestBody NewPasswordRequest req) {
        if (userService.isValidUser(req))
            if (userService.passwordsMatch(req))
                if (userService.isValidPassword(req)) {
                    req.setHashedNewPassword(req.getNewPassword1());
                    userService.updatePassword(req);
        }
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
