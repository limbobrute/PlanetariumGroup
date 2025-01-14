package com.revature.tests.service.user;

import com.revature.planetarium.entities.User;
import com.revature.planetarium.exceptions.UserFail;
import com.revature.planetarium.repository.user.UserDao;
import com.revature.planetarium.repository.user.UserDaoImp;
import com.revature.planetarium.service.user.UserService;
import com.revature.planetarium.service.user.UserServiceImp;
import com.revature.utils.Setup;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.Assert.assertThrows;

public class UserServiceAuthentication {
    UserService userService;
    UserDao userDao;
    private String positiveUsername;
    private String positivePassword;
    private String negativeUsername;
    private String negativePassword;
    private String invalidCredentialsMessage;

    @Before
    public void setup(){
        userDao = Mockito.mock(UserDaoImp.class);
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
        User user = new User(0, positiveUsername, positivePassword);
        Mockito.when(userDao.findUserByUsername(positiveUsername)).thenReturn(Optional.of(user));
        User returnedUser = userService.authenticate(user);
        Assert.assertEquals(returnedUser.getUsername(), "Batman");
        Assert.assertEquals(returnedUser.getId(), 0);
        Assert.assertNotEquals("Password is not supposed to be set", returnedUser.getPassword(), "Iamthenight1939");
    }

    @Test
    public void incorrectPasswordAuthenticationNegative(){
        User negativeUser = new User(0, positiveUsername, negativePassword);
        User positiveUser = new User(0, positiveUsername, positivePassword);
        Mockito.when(userDao.findUserByUsername(positiveUsername)).thenReturn(Optional.ofNullable(positiveUser));
        try{
            userService.authenticate(negativeUser);
            Assert.fail("Expected unhandled 'UserFail' exception id credentials'");
        }
        catch (UserFail e) {
            System.out.print(e.getMessage());
            Assert.assertEquals(e.getMessage(), invalidCredentialsMessage);
        }
    }

    @Test
    public void incorrectUsernameAuthenticationNegative(){
        User negativeUser = new User(0, negativeUsername, positivePassword);
        User positiveUser = new User(0, positiveUsername, positivePassword);
        Mockito.when(userDao.findUserByUsername(positiveUsername)).thenReturn(Optional.ofNullable(positiveUser));
        try{
            userService.authenticate(negativeUser);
            Assert.fail("Expected unhandled 'UserFail' exception with message 'Invalid credentials'");
        }
        catch (UserFail e){
            Assert.assertEquals(e.getMessage(), invalidCredentialsMessage);
        }
    }

}
