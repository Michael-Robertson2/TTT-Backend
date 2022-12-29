package com.revature.ecommerce.services;

import com.revature.ecommerce.entities.dtos.requests.NewItemRequest;
import com.revature.ecommerce.entities.dtos.enums.ItemType;
import com.revature.ecommerce.repositories.ItemRepository;
import com.revature.ecommerce.utils.custom_exceptions.InvalidItemException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ItemServiceTest {

    private ItemService sut;
    private final ItemRepository mockItemRepo = Mockito.mock(ItemRepository.class);

    @Before
    public void init() {
        sut = new ItemService(mockItemRepo);
    }

    @Test
    public void test_isValidName_givenValidName() {
        String name = "Name";
        NewItemRequest req = new NewItemRequest();
        req.setName(name);

        boolean condition = sut.isValidName(req);

        assertTrue(condition);
    }

    @Test
    public void test_isValidName_givenInvalidName() {
        String name = "";
        NewItemRequest req = new NewItemRequest();
        req.setName(name);

        InvalidItemException e = assertThrows(InvalidItemException.class, () -> {
            sut.isValidName(req);
        });

        String expectedMessage = "Item name is required";
        String actualMessage = e.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void test_isValidDescription_givenValidDescription() {
        String desc = "Item";
        NewItemRequest req = new NewItemRequest();
        req.setDescription(desc);

        boolean condition= sut.isValidDescription(req);

        assertTrue(condition);
    }

    @Test
    public void test_isValidDescription_givenInvalidDescription() {
        String desc = "";
        NewItemRequest req = new NewItemRequest();
        req.setDescription(desc);

        InvalidItemException e = assertThrows(InvalidItemException.class, () -> {
            sut.isValidDescription(req);
        });

        String expectedMessage = "Item description is required";
        String actualMessage = e.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void test_isValidstock_givenValidStock() {
        int stock = 10;
        NewItemRequest req = new NewItemRequest();
        req.setStock(stock);

        boolean condition = sut.isValidstock(req);

        assertTrue(condition);
    }

    @Test
    public void test_isValidStock_givenInvalidStock() {
        int stock = -1;
        NewItemRequest req = new NewItemRequest();
        req.setStock(stock);

        InvalidItemException e = assertThrows(InvalidItemException.class, () -> {
            sut.isValidstock(req);
        });

        String expectedMessage = "Enter a valid amount";
        String actualMessage = e.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void test_isValidMsrp_givenValidMsrp() {
        double msrp = 1.00;
        NewItemRequest req = new NewItemRequest();
        req.setMsrp(msrp);

        boolean condition = sut.isValidMsrp(req);

        assertTrue(condition);
    }

    @Test
    public void test_isValidMsrp_givenInvalidMsrp() {
        double msrp = -1.00;
        NewItemRequest req = new NewItemRequest();
        req.setMsrp(msrp);

        InvalidItemException e = assertThrows(InvalidItemException.class, () -> {
            sut.isValidMsrp(req);
        });

        String expectedMessage = "Enter a valid msrp";
        String actualMessage = e.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void test_isValidCurrentPrice_givenValidCurrentPrice() {
        double price = 1.00;
        NewItemRequest req = new NewItemRequest();
        req.setCurrent_price(price);

        boolean condition = sut.isValidCurrentPrice(req);

        assertTrue(condition);
    }

    @Test
    public void test_isValidCurrentPrice_givenInvalidCurrentPrice() {
        double price = -1.00;
        NewItemRequest req = new NewItemRequest();
        req.setCurrent_price(price);

        InvalidItemException e = assertThrows(InvalidItemException.class, () -> {
            sut.isValidCurrentPrice(req);
        });

        String expectedMessage = "Enter a valid price";
        String actualMessage = e.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void test_isValidType_givenValidType() {
        ItemType type = ItemType.Vehicles;
        NewItemRequest req = new NewItemRequest();
        req.setType(type);

        boolean condition = sut.isValidType(req);

        assertTrue(condition);
    }

    @Test
    public void test_isValidType_givenInvalidType() {
        ItemType type = null;
        NewItemRequest req = new NewItemRequest();
        req.setType(type);

        InvalidItemException e = assertThrows(InvalidItemException.class, () -> {
            sut.isValidType(req);
        });

        String expectedMessage = "Type is required";
        String actualMessage = e.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}