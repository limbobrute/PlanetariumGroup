package com.revature.tests.service.moon;

import com.revature.planetarium.entities.Moon;
import com.revature.planetarium.exceptions.MoonFail;
import com.revature.planetarium.repository.moon.MoonDao;
import com.revature.planetarium.service.moon.MoonService;
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
    MoonDao DaoObject;
    MoonService MoonService;

    String jpgFile = "";
    String webpFile = "";

    Moon ServiceTestMoon;
    Moon InvalidName;
    Moon TooManyCharacters;
    Moon InvalidPlanetId;
    Moon NonUniqueName;
    Moon BadImage;

    //Moon MockMoon;

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

        ServiceTestMoon = new Moon(0, "ServiceTestMoon", 1);
        InvalidName = new Moon(0, "Test!!", 1);
        TooManyCharacters = new Moon(0, "thisissomanycharacterswhyisthisresitrctioninplace",1);
        InvalidPlanetId = new Moon(0, "BadId", 0);
        NonUniqueName = new Moon(0, "Titan", 1);
        BadImage = new Moon(0, "BadImage", 1);
        //MockMoon = new Moon(3, "", 1);
        ServiceTestMoon.setImageData(jpgFile);
        BadImage.setImageData(webpFile);

        Setup.resetTestDatabase();
    }

    @Test
    public void ServiceLayerMoonCreationSuccess()
    {
        //Looking for boolean primitive set to TRUE
        Moon MockMoon = new Moon(3, "Test", 1);
        Mockito.when(DaoObject.createMoon(ServiceTestMoon)).thenReturn(Optional.ofNullable(MockMoon));
        Moon NewMoon = MoonService.createMoon(ServiceTestMoon);
        Assert.assertEquals(3,NewMoon.getMoonId());
    }

    @Test
    public void ServiceLayerNonUniqueName()
    {
        Moon MockMoon = new Moon(0, "Titan", 1);
        thrown.expect(MoonFail.class);
        thrown.expectMessage("unique name fail");
        Mockito.when(DaoObject.createMoon(NonUniqueName)).thenReturn(Optional.ofNullable(MockMoon));
        Mockito.when(DaoObject.readMoon(MockMoon.getMoonName())).thenReturn(Optional.ofNullable(MockMoon));
        Moon NewMoon = MoonService.createMoon(NonUniqueName);
    }

    @Test
    public void ServiceLayerInvalidMoonName()
    {
        Moon MockMoon = new Moon (0, "Test!!!", 1);
        thrown.expect(MoonFail.class);
        thrown.expectMessage("Invalid moon name");
        Mockito.when(DaoObject.createMoon(InvalidName)).thenReturn(Optional.ofNullable(MockMoon));
        Moon NewMoon = MoonService.createMoon(InvalidName);
    }

    @Test
    public void ServiceLayerTooManyCharacters()
    {
        //Looking for unhandled MoonFail Exception with message "character length fail"
        Moon MockMoon = new Moon(0, "thisissomanycharacterswhyisthisresitrctioninplace", 1);
        thrown.expect(MoonFail.class);
        thrown.expectMessage("character length fail");
        Mockito.when(DaoObject.createMoon(TooManyCharacters)).thenReturn(Optional.ofNullable(MockMoon));
        Moon NewMoon = MoonService.createMoon(TooManyCharacters);

    }


    @Test
    public void ServiceLayerInvalidMoonImage()
    {
        //Looking for unhandled MoonFail Exception with message "Invalid file type"
        Moon MockMoon = new Moon(0, "BadImage", 1);
        MockMoon.setImageData(webpFile);
        thrown.expect(MoonFail.class);
        thrown.expectMessage("Invalid file type");
        Mockito.when(DaoObject.createMoon(BadImage)).thenReturn(Optional.ofNullable(MockMoon));
        Moon NewMoon = MoonService.createMoon(BadImage);
    }
}
