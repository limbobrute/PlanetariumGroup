package com.revature.tests;

import com.revature.planetarium.entities.Moon;
import com.revature.planetarium.exceptions.MoonFail;
import com.revature.utils.Setup;
import com.revature.planetarium.repository.moon.MoonDaoImp;
import com.revature.planetarium.service.moon.MoonServiceImp;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.Assert;
import org.junit.rules.ExpectedException;
import org.sqlite.SQLiteException;

public class MoonCreationTest
{
    MoonDaoImp DaoObject;
    MoonServiceImp MoonService;
    Moon TestMoon;
    Moon InvalidName;
    Moon TooManyCharacters;
    Moon InvalidPlanetId;
    Moon NonUniqueName;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void Setup()
    {
        DaoObject = new MoonDaoImp();
        MoonService = new MoonServiceImp(DaoObject);
        TestMoon = new Moon(0, "Test", 1);
        InvalidName = new Moon (0, "Test!!", 1);
        TooManyCharacters = new Moon(0, "thisissomanycharacterswhyisthisresitrctioninplace",1);
        InvalidPlanetId = new Moon(0, "BadId", 0);
        NonUniqueName = new Moon(0, "Titan", 1);
        Setup.resetTestDatabase();
    }

    //Service Layer Tests
    @Test
    public void ServiceLayerMoonCreationSuccess()
    {
        //Looking for boolean primitive set to TRUE
        Moon NewMoon = MoonService.createMoon(TestMoon);
        Assert.assertEquals(3,NewMoon.getMoonId());
    }

    @Test
    public void ServiceLayerNonUniqueName()
    {
        thrown.expect(MoonFail.class);
        thrown.expectMessage("unique name fail");
        Moon NewMoon = MoonService.createMoon(NonUniqueName);
    }

    @Test
    public void ServiceLayerInvalidMoonName()
    {
        thrown.expect(MoonFail.class);
        Moon NewMoon = MoonService.createMoon(InvalidName);
    }

    @Test
    public void ServiceLayerTooManyCharacters()
    {
        //Looking for unhandled MoonFail Exception with message "character length fail"
        thrown.expect(MoonFail.class);
        thrown.expectMessage("character length fail");
        Moon NewMoon = MoonService.createMoon(TooManyCharacters);

    }

    @Test
    public void ServiceLayerInvalidOwnerID()
    {
        //Looking for unhandled MoonFail Exception with message "Invalid planet ID"
        thrown.expect(MoonFail.class);
        //
        Moon NewMoon = MoonService.createMoon(InvalidPlanetId);
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
