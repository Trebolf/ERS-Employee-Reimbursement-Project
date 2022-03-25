package services;

import models.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import repositories.UserDAO;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    private UserService userService;
    //mocking methods that are not being tested to isolate
    private UserDAO userDAO = Mockito.mock(UserDAO.class);

    public UserServiceTest() {
        this.userService = new UserService(userDAO);
    }

    @Test
    void validateCredentialsInvalidUsername() {
        //arrange
        String expectedUsername = "incorrectusername";
        String expectedPassword = "password1";
        User expectedOutput = null;
        Mockito.when(userDAO.getUserGivenUsername(expectedUsername)).thenReturn(expectedOutput);

        //act
        User actualOutput = userService.validateCredentials(expectedUsername, expectedPassword);

        //assert
        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void validateCredentialsInvalidPassword() {
        String expectedUsername = "correctusername";
        String expectedPassword = "password1";
        User expectedOutput = null;
        User userFromDB= new User(999, "correctusername", "pass1234", "role");
        Mockito.when(userDAO.getUserGivenUsername(expectedUsername)).thenReturn(userFromDB);

        User actualOutput = userService.validateCredentials(expectedUsername, expectedPassword);

        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void validateCredentialsValid() {
        String expectedUsername = "correctusername";
        String expectedPassword = "correctpassword";
        User expectedOutput = new User(999, expectedUsername, expectedPassword, "last");
        Mockito.when(userDAO.getUserGivenUsername(expectedUsername)).thenReturn(expectedOutput);

        User actualOutput = userService.validateCredentials(expectedUsername, expectedPassword);

        assertEquals(expectedOutput, actualOutput);
    }
}