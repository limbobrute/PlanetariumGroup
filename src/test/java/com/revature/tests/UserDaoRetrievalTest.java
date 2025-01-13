package com.revature.tests;
import com.revature.planetarium.entities.User;
import com.revature.planetarium.exceptions.UserFail;
import com.revature.planetarium.repository.user.UserDaoImp;
import com.revature.utils.Setup;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;

import java.util.Optional;
public class UserDaoRetrievalTest {
    UserDaoImp userDao;
    private String validUsername;
    private String invalidUsername;

    @Before
    public void setup(){
        userDao = new UserDaoImp();
        validUsername = "Batman";
        invalidUsername = "NotARealUsername";
        Setup.resetTestDatabase();
    }

    @Test
    public void retrieveUserByUsernamePositive(){
        Optional<User> newUser = userDao.findUserByUsername(validUsername);
        Assert.assertEquals(newUser.get().getUsername(), validUsername);
    }

    @Test
    public void retrieveUserByUsernameNegative(){
        Optional<User> newUser = userDao.findUserByUsername(invalidUsername);
        try{
            Assert.assertEquals(newUser.get().getUsername(), validUsername);
        }
        catch (Exception e){
            Assert.assertEquals(e.getMessage(), "No value present");
        }

    }
}
