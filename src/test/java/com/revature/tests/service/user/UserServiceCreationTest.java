package com.revature.tests.service.user;

import com.revature.planetarium.entities.User;
import com.revature.planetarium.exceptions.UserFail;
import com.revature.planetarium.repository.user.UserDao;
import com.revature.planetarium.repository.user.UserDaoImp;
import com.revature.planetarium.service.user.UserServiceImp;
import com.revature.utils.Setup;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.mockito.Mockito;

import java.util.Optional;

public class UserServiceCreationTest {
    UserServiceImp userService;
    UserDao userDao;
    private String validUserMessage;
    private String positiveUsername;
    private String positivePassword;
    private String duplicateUsername;
    private String shortUsername;
    private String longUsername;
    private String startsWithNumber;
    private String invalidCharacters;
    private String shortPassword;
    private String longPassword;
    private String startsWithNumberPass;
    private String invalidCharactersPass;
    private String noCaps;
    private String noLower;
    private String noNumber;
    private String invalidUsernameMessage;
    private String invalidPasswordMessage;
    private User mockUser;
    @Before
    public void setup(){
        userDao = Mockito.mock(UserDaoImp.class);
        userService = new UserServiceImp(userDao);
        mockUser = new User();
        positiveUsername = "Super_man-2001";
        positivePassword = "Krypton-was_2000";
        validUserMessage = "User created successfully";
        invalidUsernameMessage = "Invalid username";
        invalidPasswordMessage = "Invalid password";
        duplicateUsername = "Batman";
        shortUsername = "Bane";
        longUsername = "wonder_woman_for_the_DC_theming";
        startsWithNumber = "2face";
        invalidCharacters = "joker!!!!!!?)";
        shortPassword = "b0Ts";
        longPassword = "AlfredIsTheBestButlerToExist111";
        startsWithNumberPass = "3atman";
        invalidCharactersPass = "A1fredIsTheBestButlerToExist!!";
        noCaps = "batman1";
        noLower = "BATMAN1";
        noNumber = "Robin";
        Setup.resetTestDatabase();
    }

    @Test
    public void createUserPostive(){
        Mockito.when(userDao.createUser(new User(0, positiveUsername, positivePassword))).thenReturn(Optional.of(mockUser));
        Mockito.when(userDao.findUserByUsername(positiveUsername)).thenReturn(Optional.empty());
        String message = userService.createUser(new User(0, positiveUsername, positivePassword));
        Assert.assertEquals(validUserMessage, message);
    }

    @Test
    public void duplicateUsernameNegative(){
        try {
            Mockito.when(userDao.createUser(new User(0, duplicateUsername, positivePassword))).thenReturn(Optional.of(mockUser));
            Mockito.when(userDao.findUserByUsername(duplicateUsername)).thenReturn(Optional.of(mockUser));
            String message = userService.createUser(new User(0, duplicateUsername, positivePassword));
            Assert.fail("Expected UserFail, but no exception was thrown");
        }
        catch (UserFail e){
            Assert.assertEquals(invalidUsernameMessage, e.getMessage());
        }

    }

    @Test
    public void shortUsernameNegative(){
        try{
            Mockito.when(userDao.createUser(new User(0, shortUsername, positivePassword))).thenReturn(Optional.of(mockUser));
            Mockito.when(userDao.findUserByUsername(shortUsername)).thenReturn(Optional.empty());
            String message = userService.createUser(new User(0, shortUsername, positivePassword));
            Assert.fail("Expected UserFail, but no exception was thrown");

        }
        catch (UserFail e){
            Assert.assertEquals(invalidUsernameMessage, e.getMessage());
        }
    }

    @Test
    public void longUsernameNegative(){
        try {
            Mockito.when(userDao.createUser(new User(0, longUsername, positivePassword))).thenReturn(Optional.of(mockUser));
            Mockito.when(userDao.findUserByUsername(longUsername)).thenReturn(Optional.empty());
            String message = userService.createUser(new User(0, longUsername, positivePassword));
            Assert.fail("Expected UserFail, but no exception was thrown");
        }
        catch (UserFail e){
            Assert.assertEquals(invalidUsernameMessage, e.getMessage());
        }
    }

    @Test
    public void usernameStartsWithNumberNegative(){
        try {
            Mockito.when(userDao.createUser(new User(0, startsWithNumber, positivePassword))).thenReturn(Optional.of(mockUser));
            Mockito.when(userDao.findUserByUsername(startsWithNumber)).thenReturn(Optional.empty());
            String message = userService.createUser(new User(0, startsWithNumber, positivePassword));
            Assert.fail("Expected UserFail, but no exception was thrown");
        }
        catch (UserFail e){
            Assert.assertEquals(invalidUsernameMessage, e.getMessage());
        }
    }

    @Test
    public void usernameInvalidCharactersNegative(){
        try {
            Mockito.when(userDao.createUser(new User(0, invalidCharacters, positivePassword))).thenReturn(Optional.of(mockUser));
            Mockito.when(userDao.findUserByUsername(invalidCharacters)).thenReturn(Optional.empty());
            String message = userService.createUser(new User(0, invalidCharacters, positivePassword));
            Assert.fail("Expected UserFail, but no exception was thrown");
        }
        catch (UserFail e){
            Assert.assertEquals(invalidUsernameMessage, e.getMessage());
        }
    }

    @Test
    public void shortPasswordNegative(){
        try {
            Mockito.when(userDao.createUser(new User(0, positiveUsername, shortPassword))).thenReturn(Optional.of(mockUser));
            Mockito.when(userDao.findUserByUsername(positiveUsername)).thenReturn(Optional.empty());
            String message = userService.createUser(new User(0, positiveUsername, shortPassword));
            Assert.fail("Expected UserFail, but no exception was thrown");
        }
        catch (UserFail e){
            Assert.assertEquals(invalidPasswordMessage, e.getMessage());
        }
    }

    @Test
    public void longPasswordNegative(){
        try {
            Mockito.when(userDao.createUser(new User(0, positiveUsername, longPassword))).thenReturn(Optional.of(mockUser));
            Mockito.when(userDao.findUserByUsername(positiveUsername)).thenReturn(Optional.empty());
            String message = userService.createUser(new User(0, positiveUsername, longPassword));
            Assert.fail("Expected UserFail, but no exception was thrown");
        }
        catch (UserFail e){
            Assert.assertEquals(invalidPasswordMessage, e.getMessage());
        }
    }

    @Test
    public void passwordStartsWithNumberNegative(){
        try {
            Mockito.when(userDao.createUser(new User(0, positiveUsername, startsWithNumberPass))).thenReturn(Optional.of(mockUser));
            Mockito.when(userDao.findUserByUsername(positiveUsername)).thenReturn(Optional.empty());
            String message = userService.createUser(new User(0, positiveUsername, startsWithNumberPass));
            Assert.fail("Expected UserFail, but no exception was thrown");
        }
        catch (UserFail e){
            Assert.assertEquals(invalidPasswordMessage, e.getMessage());
        }
    }

    @Test
    public void passwordInvalidCharactersNegative(){
        try {
            Mockito.when(userDao.createUser(new User(0, positiveUsername, invalidCharactersPass))).thenReturn(Optional.of(mockUser));
            Mockito.when(userDao.findUserByUsername(positiveUsername)).thenReturn(Optional.empty());
            String message = userService.createUser(new User(0, positiveUsername, invalidCharactersPass));
            Assert.fail("Expected UserFail, but no exception was thrown");
        }
        catch (UserFail e){
            Assert.assertEquals(invalidPasswordMessage, e.getMessage());
        }
    }

    @Test
    public void noCapsNegative(){
        try {
            Mockito.when(userDao.createUser(new User(0, positiveUsername, noCaps))).thenReturn(Optional.of(mockUser));
            Mockito.when(userDao.findUserByUsername(positiveUsername)).thenReturn(Optional.empty());
            String message = userService.createUser(new User(0, positiveUsername, noCaps));
            Assert.fail("Expected UserFail, but no exception was thrown");
        }
        catch (UserFail e){
            Assert.assertEquals(invalidPasswordMessage, e.getMessage());
        }
    }

    @Test
    public void noLowerNegative(){
        try {
            Mockito.when(userDao.createUser(new User(0, positiveUsername, noLower))).thenReturn(Optional.of(mockUser));
            Mockito.when(userDao.findUserByUsername(positiveUsername)).thenReturn(Optional.empty());
            String message = userService.createUser(new User(0, positiveUsername, noLower));
            Assert.fail("Expected UserFail, but no exception was thrown");
        }
        catch (UserFail e){
            Assert.assertEquals(invalidPasswordMessage, e.getMessage());
        }
    }

    @Test
    public void noNumberNegative(){
        try {
            Mockito.when(userDao.createUser(new User(0, positiveUsername, noNumber))).thenReturn(Optional.of(mockUser));
            Mockito.when(userDao.findUserByUsername(positiveUsername)).thenReturn(Optional.empty());
            String message = userService.createUser(new User(0, positiveUsername, noNumber));
            Assert.fail("Expected UserFail, but no exception was thrown");
        }
        catch (UserFail e){
            Assert.assertEquals(invalidPasswordMessage, e.getMessage());
        }
    }
}
