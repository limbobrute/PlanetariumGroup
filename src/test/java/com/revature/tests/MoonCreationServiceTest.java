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
import org.mockito.Mockito;

import java.util.Optional;

public class MoonCreationServiceTest
{
    MoonDaoImp DaoObject;
    MoonServiceImp MoonService;

    String jpgFile = "";
    String webpFile = "";

    Moon ServiceTestMoon;
    Moon InvalidName;
    Moon TooManyCharacters;
    Moon InvalidPlanetId;
    Moon NonUniqueName;
    Moon BadImage;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void Setup()
    {
        DaoObject = Mockito.mock(MoonDaoImp.class);
        MoonService = new MoonServiceImp(DaoObject);

        String jpg = "src\\test\\resources\\Celestial-Images\\moons-1.jpg";
        String webp = "src\\test\\resources\\Celestial-Images\\Testwebp.webp";
        jpgFile = FileEncoder.encoder(jpg);
        webpFile = FileEncoder.encoder(webp);

        ServiceTestMoon = new Moon(0, "Test", 1);
        InvalidName = new Moon(0, "Test!!", 1);
        TooManyCharacters = new Moon(0, "thisissomanycharacterswhyisthisresitrctioninplace",1);
        InvalidPlanetId = new Moon(0, "BadId", 0);
        NonUniqueName = new Moon(0, "Titan", 1);
        BadImage = new Moon(0, "BadImage", 1);
        ServiceTestMoon.setImageData(jpgFile);
        BadImage.setImageData(webpFile);

        Setup.resetTestDatabase();
    }

    @Test
    public void ServiceLayerMoonCreationSuccess()
    {
        //Looking for boolean primitive set to TRUE
        Moon NewMoon = MoonService.createMoon(ServiceTestMoon);
        Assert.assertEquals(3,NewMoon.getMoonId());
        /*boolean check = MoonService.createMoon(ServiceTestMoon);
        Assert.assertTrue(true, check);*/
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
        thrown.expectMessage("Invalid moon name");
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
        thrown.expect(MoonFail.class);
        thrown.expectMessage("Invalid file type");
        Moon NewMoon = MoonService.createMoon(BadImage);
    }
}
