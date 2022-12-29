package com.revature.ecommerce.services;

import com.revature.ecommerce.entities.dtos.requests.NewPasswordRequest;
import com.revature.ecommerce.entities.dtos.requests.NewUserRequest;
import com.revature.ecommerce.repositories.UserRepository;
import com.revature.ecommerce.utils.custom_exceptions.InvalidUserException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserServiceTest {

    private UserService sut;
    private final UserRepository mockUserRepo = Mockito.mock(UserRepository.class);

    @Before
    public void init() {
        sut = new UserService(mockUserRepo);
    }

    @Test
    public void test_passwordsMatchUserReq_givenPasswordsMatch() {
        String password1 = "Password1";
        String password2 = "Password1";
        NewUserRequest req = new NewUserRequest(null, password1, password2, null, null);

        boolean condition = sut.passwordsMatch(req);

        assertTrue(condition);
    }

    @Test
    public void test_passWordsMatchUserReq_givenPasswordDoNotMatch() {
        String password1 = "Password1";
        String password2 = "Password2";
        NewUserRequest req = new NewUserRequest(null, password1, password2, null, null);

        InvalidUserException e = assertThrows(InvalidUserException.class, () -> {
            sut.passwordsMatch(req);
        });

        String expectedMessage = "Passwords do not match";
        String actualMessage = e.getMessage();
        boolean condition = expectedMessage.contains(actualMessage);

        assertTrue(condition);
    }

    @Test
    public void test_passwordsMatchPasswordReq_givenPasswordsMatch() {
        String password1 = "Password1";
        String password2 = "Password1";
        NewPasswordRequest req = new NewPasswordRequest(null, "oldPassword1", password1, password2);

        boolean condition = sut.passwordsMatch(req);

        assertTrue(condition);
    }

    @Test
    public void test_passWordsMatchPasswordReq_givenPasswordDoNotMatch() {
        String password1 = "Password1";
        String password2 = "Password2";
        NewPasswordRequest req = new NewPasswordRequest(null, "oldPassword1", password1, password2);

        InvalidUserException e = assertThrows(InvalidUserException.class, () -> {
            sut.passwordsMatch(req);
        });

        String expectedMessage = "Passwords do not match";
        String actualMessage = e.getMessage();
        boolean condition = expectedMessage.contains(actualMessage);

        assertTrue(condition);
    }


    @Test
    public void test_isValidPasswordUserReq_givenValidPassword() {
        String password = "Password1";
        NewUserRequest req = new NewUserRequest(null, password, password, null, null);

        boolean condition = sut.isValidPassword(req);

        assertTrue(condition);
    }

    @Test
    public void isValidEmail() {
    }

    @Test
    public void test_isUniqueEmailUserReq_givenUniqueEmail() {
        String email = "qwerty@example.com";
        NewUserRequest req = new NewUserRequest(email, null, null, null, null);
        List<String> stubbedEmails = Arrays.asList("test1@test.com", "test2@test.com", "test3@test.com");
        UserService spySut = Mockito.spy(sut);

        Mockito.when(mockUserRepo.findAllEmails()).thenReturn(stubbedEmails);

        boolean condition = spySut.isUniqueEmail(req);

        assertTrue(condition);
    }

    @Test
    public void test_isUniqueEmailUserReq_givenDuplicateEmail() {
        String email = "test1@test.com";
        NewUserRequest req = new NewUserRequest(email, null, null, null, null);
        List<String> stubbedEmails = Arrays.asList("test1@test.com", "test2@test.com", "test3@test.com");
        UserService spySut = Mockito.spy(sut);

        Mockito.when(mockUserRepo.findAllEmails()).thenReturn(stubbedEmails);

        InvalidUserException e = assertThrows(InvalidUserException.class, () -> {
           spySut.isUniqueEmail(req);
        });

        String expectedMessage = "There is already an account associated with this email";
        String actualMessage = e.getMessage();
        boolean condition = expectedMessage.contains(actualMessage);

        assertTrue(condition);
    }

    @Test
    public void isValidCardNumber() {
    }

    @Test
    public void isValidExpDate() {
    }

    @Test
    public void cardAndDate() {
    }

    @Test
    public void isValidUser() {
    }
}