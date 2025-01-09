package com.revature.tests;

import com.revature.planetarium.entities.Moon;
import com.revature.planetarium.exceptions.MoonFail;
import com.revature.utils.Setup;
import com.revature.utils.FileEncoder;
import com.revature.planetarium.repository.moon.MoonDaoImp;
import com.revature.planetarium.service.moon.MoonServiceImp;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.Assert;
import org.junit.rules.ExpectedException;
import org.sqlite.SQLiteException;

import java.util.Optional;

public class MoonCreationTest
{
    MoonDaoImp DaoObject;
    MoonServiceImp MoonService;

    String jpgFile = "";
    String webpFile = "";
    /*Test Moon variables for the Service Layer*/
    Moon ServiceTestMoon;
    Moon InvalidName;
    Moon TooManyCharacters;
    Moon InvalidPlanetId;
    Moon NonUniqueName;
    Moon BadImage;

    /*Test Moon variables for the Repository Layer*/
    Moon DAOTestMoon;
    Moon DAOInvalidName;
    Moon DAOInvalidID;
    Moon DAOBadImage;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void Setup()
    {
        DaoObject = new MoonDaoImp();
        MoonService = new MoonServiceImp(DaoObject);

        String jpg = "src\\test\\resources\\Celestial-Images\\moons-1.jpg";
        String webp = "src\\test\\resources\\Celestial-Images\\Testwebp.webp";
        jpgFile = FileEncoder.encoder(jpg);
        webpFile = FileEncoder.encoder(webp);

        /*Test Moon data for the Service Layer*/
        ServiceTestMoon = new Moon(0, "Test", 1);
        InvalidName = new Moon(0, "Test!!", 1);
        TooManyCharacters = new Moon(0, "thisissomanycharacterswhyisthisresitrctioninplace",1);
        InvalidPlanetId = new Moon(0, "BadId", 0);
        NonUniqueName = new Moon(0, "Titan", 1);
        BadImage = new Moon(0, "BadImage", 1);
        BadImage.setImageData(jpgFile);//Need to find a way to have this be garbage

        /*Test Moon data for the Repository Layer*/
        DAOTestMoon = new Moon(0, "DAOtest", 1);
        DAOTestMoon.setImageData(jpgFile);
        DAOInvalidName = new Moon(0, "DAOtestMoon!", 1);
        DAOInvalidID = new Moon(0, "DAOBadId", 0);
        DAOBadImage = new Moon(0, "DAOBadImage", 1);
        DAOBadImage.setImageData(webpFile);//Need to find a way to have this be garbage
        Setup.resetTestDatabase();
    }

    //Service Layer Tests
    @Test
    public void ServiceLayerMoonCreationSuccess()
    {
        //Looking for boolean primitive set to TRUE
        Moon NewMoon = MoonService.createMoon(ServiceTestMoon);
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
    public void ServiceLayerInvalidMoonImage()
    {
        //Looking for unhandled MoonFail Exception with message "Invalid file type"
    }

    //Repository Layer Tests
    @Test
    public void RepoLayerMoonCreationSuccess()
    {
        //Looking for Optional object that contains a Moon object with a unique moonId
        Optional<Moon> NewMoon = DaoObject.createMoon(DAOTestMoon);
        Assert.assertTrue(NewMoon.isPresent());
    }

    @Test
    public void RepoLayerInvalidMoonName()
    {
        //Looking for unhandled MoonFail Exception with message "Invalid moon name"
        thrown.expect(MoonFail.class);
        Optional<Moon> NewMoon = DaoObject.createMoon(DAOInvalidName);
    }

    @Test
    public void RepoLayerInvalidOwnerId()
    {
        //Looking for unhandled MoonFail Exception with message "Invalid planet ID"
        thrown.expect(MoonFail.class);
        Optional<Moon> NewMoon = DaoObject.createMoon(DAOInvalidID);
    }

    @Test
    public void RepoLayerInvalidMoonImage()
    {
        thrown.expect(MoonFail.class);
        thrown.expectMessage("Invalid file type");
        Optional<Moon> NewMoon = DaoObject.createMoon(DAOBadImage);
    }
}
