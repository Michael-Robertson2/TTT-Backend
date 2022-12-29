package com.revature.ecommerce.services;

import com.revature.ecommerce.entities.junctions.Cart;
import com.revature.ecommerce.repositories.CartRepository;
import com.revature.ecommerce.repositories.ItemRepository;
import com.revature.ecommerce.repositories.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.Assert.*;

public class CartServiceTest {

    private CartService sut;
    private final CartRepository mockCartRepo = Mockito.mock(CartRepository.class);
    private final UserRepository mockUserRepo = Mockito.mock(UserRepository.class);
    private final ItemRepository mockItemRepo = Mockito.mock(ItemRepository.class);

    @Before
    public void init() {
        sut = new CartService(mockCartRepo, mockUserRepo, mockItemRepo);
    }

    @Test
    public void isValidItem() {
    }

    @Test
    public void isValidItemAmount() {
    }

    @Test
    public void test_isUnique_givenUnique() {

        //Optional<Cart> entry = new Optional(new Cart());
    }

    @Test
    public void isPresent() {
    }
}