package com.revature.tests;

import com.revature.planetarium.entities.Moon;
import com.revature.planetarium.repository.moon.MoonDaoImp;
import com.revature.planetarium.service.moon.MoonServiceImp;
import org.junit.Test;
import org.junit.Assert;

public class MoonCreationTest
{
    Moon TestMoon;

    //Service Layer Tests
    @Test
    public void ServiceLayerMoonCreationSuccess()
    {
        //Looking for boolean primitive set to TRUE
    }

    @Test
    public void ServiceLayerInvalidMoonName()
    {
        //Looking for unhandled MoonFail Exception with message "Invalid moon name"
    }

    @Test
    public void ServiceLayerInvalidOwnerID()
    {
        //Looking for unhandled MoonFail Exception with message "Invalid planet ID"
    }

    @Test
    public void ServiceLayerInvalidMoonImage()
    {
        //Looking for unhandled MoonFail Exception with message "Invalid file type"
    }

    //Repository Layer Tests
    @Test
    public void RepoLayerMoonCreationSuccess()
    {
        //Looking for Optional object that contains a Moon object with a unique moonId
    }

    @Test
    public void RepoLayerInvalidMoonName()
    {
        //Looking for unhandled MoonFail Exception with message "Invalid moon name"
    }

    @Test
    public void RepoLayerInvalidOwnerId()
    {
        //Looking for unhandled MoonFail Exception with message "Invalid planet ID"
    }

    @Test
    public void RepoLayerInvalidMoonImage()
    {
        //Looking for unhandled MoonFail Exception with message "Invalid file type"
    }
}
