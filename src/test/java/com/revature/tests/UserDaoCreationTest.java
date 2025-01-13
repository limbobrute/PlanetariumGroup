package com.revature.tests;

import com.revature.planetarium.entities.User;
import com.revature.planetarium.exceptions.UserFail;
import com.revature.planetarium.repository.user.UserDaoImp;
import com.revature.utils.Setup;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;

import java.util.Optional;

public class UserDaoCreationTest {
    UserDaoImp userDao;
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


    @Before
    public void setup(){
        userDao = new UserDaoImp();
        positiveUsername = "Super_man-2001";
        positivePassword = "Krypton-was_2000";
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
        Optional<User> newUser = userDao.createUser(new User(0, positiveUsername, positivePassword));
        Assert.assertEquals(newUser.get().getUsername(), positiveUsername);
        Assert.assertEquals(newUser.get().getPassword(), positivePassword);
        Assert.assertNotEquals(newUser.get().getId(), 0);
    }

    @Test
    public void duplicateUsernameNegative(){
        try {
            Optional<User> newUser = userDao.createUser(new User(0, duplicateUsername, positivePassword));
            Assert.fail("Expected UserFail, but no exception was thrown");
        }
        catch (UserFail e){
            Assert.assertEquals(invalidUsernameMessage, e.getMessage());
        }

    }

    @Test
    public void shortUsernameNegative(){
        try{
            Optional<User> newUser = userDao.createUser(new User(0, shortUsername, positivePassword));
            Assert.fail("Expected UserFail, but no exception was thrown");

        }
        catch (UserFail e){
            Assert.assertEquals(invalidUsernameMessage, e.getMessage());
        }
    }

    @Test
    public void longUsernameNegative(){
        try {
            Optional<User> newUser = userDao.createUser(new User(0, longUsername, positivePassword));
            Assert.fail("Expected UserFail, but no exception was thrown");
        }
        catch (UserFail e){
            Assert.assertEquals(invalidUsernameMessage, e.getMessage());
        }
    }

    @Test
    public void usernameStartsWithNumberNegative(){
        try {
            Optional<User> newUser = userDao.createUser(new User(0, startsWithNumber, positivePassword));
            Assert.fail("Expected UserFail, but no exception was thrown");
        }
        catch (UserFail e){
            Assert.assertEquals(invalidUsernameMessage, e.getMessage());
        }
    }

    @Test
    public void usernameInvalidCharactersNegative(){
        try {
            Optional<User> newUser = userDao.createUser(new User(0, invalidCharacters, positivePassword));
            Assert.fail("Expected UserFail, but no exception was thrown");
        }
        catch (UserFail e){
            Assert.assertEquals(invalidUsernameMessage, e.getMessage());
        }
    }

    @Test
    public void shortPasswordNegative(){
        try {
            Optional<User> newUser = userDao.createUser(new User(0, positiveUsername, shortPassword));
            Assert.fail("Expected UserFail, but no exception was thrown");
        }
        catch (UserFail e){
            Assert.assertEquals(invalidPasswordMessage, e.getMessage());
        }
    }

    @Test
    public void longPasswordNegative(){
        try {
            Optional<User> newUser = userDao.createUser(new User(0, positiveUsername, longPassword));
            Assert.fail("Expected UserFail, but no exception was thrown");
        }
        catch (UserFail e){
            Assert.assertEquals(invalidPasswordMessage, e.getMessage());
        }
    }

    @Test
    public void passwordStartsWithNumberNegative(){
        try {
            Optional<User> newUser = userDao.createUser(new User(0, positiveUsername, startsWithNumberPass));
            Assert.fail("Expected UserFail, but no exception was thrown");
        }
        catch (UserFail e){
            Assert.assertEquals(invalidPasswordMessage, e.getMessage());
        }
    }

    @Test
    public void passwordInvalidCharactersNegative(){
        try {
            Optional<User> newUser = userDao.createUser(new User(0, positiveUsername, invalidCharactersPass));
            Assert.fail("Expected UserFail, but no exception was thrown");
        }
        catch (UserFail e){
            Assert.assertEquals(invalidPasswordMessage, e.getMessage());
        }
    }

    @Test
    public void noCapsNegative(){
        try {
            Optional<User> newUser = userDao.createUser(new User(0, positiveUsername, noCaps));
            Assert.fail("Expected UserFail, but no exception was thrown");
        }
        catch (UserFail e){
            Assert.assertEquals(invalidPasswordMessage, e.getMessage());
        }
    }

    @Test
    public void noLowerNegative(){
        try {
            Optional<User> newUser = userDao.createUser(new User(0, positiveUsername, noLower));
            Assert.fail("Expected UserFail, but no exception was thrown");
        }
        catch (UserFail e){
            Assert.assertEquals(invalidPasswordMessage, e.getMessage());
        }
    }

    @Test
    public void noNumberNegative(){
        try {
            Optional<User> newUser = userDao.createUser(new User(0, positiveUsername, noNumber));
            Assert.fail("Expected UserFail, but no exception was thrown");
        }
        catch (UserFail e){
            Assert.assertEquals(invalidPasswordMessage, e.getMessage());
        }
    }

}
