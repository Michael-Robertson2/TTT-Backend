package com.revature.ecommerce.controllers;

import com.revature.ecommerce.entities.dtos.requests.NewCartRequest;
import com.revature.ecommerce.entities.dtos.responses.CartPrincipal;
import com.revature.ecommerce.entities.dtos.responses.Principal;
import com.revature.ecommerce.services.CartService;
import com.revature.ecommerce.services.TokenService;
import com.revature.ecommerce.utils.custom_exceptions.InvalidAuthException;
import com.revature.ecommerce.utils.custom_exceptions.InvalidItemException;
import com.revature.ecommerce.utils.custom_exceptions.InvalidUserException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;
    private final TokenService tokenService;

    public CartController(CartService cartService, TokenService tokenService) {
        this.cartService = cartService;
        this.tokenService = tokenService;
    }

    @PostMapping
    public void addToCart(@RequestBody NewCartRequest req, HttpServletRequest hReq) {
        Principal principal = tokenService.isLoggedIn(hReq);

        req.setUserId(principal.getId());

        if (cartService.isValidItem(req))
            if (cartService.isValidItemAmount(req))
                if (cartService.isUnique(req))
                    cartService.addToCart(req);
    }

    @PutMapping
    public void updateAmount(@RequestBody NewCartRequest req, HttpServletRequest hReq) {
        Principal principal = tokenService.isLoggedIn(hReq);

        req.setUserId(principal.getId());

        if (cartService.isPresent(req))
            if (cartService.isValidItemAmount(req))
                cartService.updateAmount(req);
    }

    @GetMapping
    public List<CartPrincipal> getCart(HttpServletRequest req) {
        Principal principal = tokenService.isLoggedIn(req);

        return cartService.getCart(principal);
    }

    @DeleteMapping
    public void deleteFromCart(@RequestParam String itemId, HttpServletRequest hReq) {
        Principal principal = tokenService.isLoggedIn(hReq);
        NewCartRequest req = new NewCartRequest(principal.getId(), itemId);

        if (cartService.isPresent(req))
            cartService.delete(req);
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

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidItemException.class)
    public InvalidItemException handleItemException(InvalidItemException e) {
        return e;
    }
}
