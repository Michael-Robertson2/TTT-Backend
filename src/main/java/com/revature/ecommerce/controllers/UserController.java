package com.revature.ecommerce.controllers;

import com.revature.ecommerce.dtos.requests.NewUserRequest;
import com.revature.ecommerce.services.UserService;
import com.revature.ecommerce.utils.custom_exceptions.InvalidUserException;
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
                    if (userService.isUniqueEmail(req))
                        userService.signup(req);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidUserException.class)
    public InvalidUserException handleUserException(InvalidUserException e) {
        return e;
    }
}
