package com.revature.tests;

import com.revature.planetarium.entities.User;
import com.revature.planetarium.exceptions.UserFail;
import com.revature.planetarium.repository.user.UserDao;
import com.revature.planetarium.repository.user.UserDaoImp;
import com.revature.planetarium.service.user.UserServiceImp;
import com.revature.utils.Setup;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;

import java.util.Optional;
public class UserServiceAuthentication {
    UserServiceImp userService;
    UserDaoImp userDao;
    private String positiveUsername;
    private String positivePassword;
    private String negativeUsername;
    private String negativePassword;
    private String invalidCredentialsMessage;

    @Before
    public void setup(){
        userDao = new UserDaoImp();
        userService = new UserServiceImp(userDao);
        positiveUsername = "Batman";
        positivePassword = "Iamthenight1939";
        negativeUsername = "NotARealUsername";
        negativePassword = "NotARealPassword1";
        invalidCredentialsMessage = "Invalid credentials";
        Setup.resetTestDatabase();
    }

    @Test
    public void authenticationPositive(){
        User user = userService.authenticate(new User(0, positiveUsername, positivePassword));
        Assert.assertEquals(user.getUsername(), "Batman");
        Assert.assertEquals(user.getId(), 1);
        Assert.assertNotEquals(user.getPassword(), "Iamthenight1939");
    }

    @Test
    public void incorrectPasswordAuthenticationNegative(){
        try{
            User user = userService.authenticate(new User(0, positiveUsername, negativePassword));
            Assert.fail("Expected unhandled 'UserFail' exception with message 'Invalid credentials'");
        }
        catch (UserFail e) {
            Assert.assertEquals(e.getMessage(), invalidCredentialsMessage);
        }
    }

    @Test
    public void incorrectUsernameAuthenticationNegative(){
        try{
            User user = userService.authenticate(new User(0, negativeUsername, positivePassword));
            Assert.fail("Expected unhandled 'UserFail' exception with message 'Invalid credentials'");
        }
        catch (UserFail e){
            Assert.assertEquals(e.getMessage(), invalidCredentialsMessage);
        }
    }

}
