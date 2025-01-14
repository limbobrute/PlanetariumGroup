package com.revature.tests.repository.moon;

import com.revature.planetarium.entities.Moon;
import com.revature.planetarium.exceptions.MoonFail;
import com.revature.utils.Setup;
import com.revature.utils.FileEncoder;
import com.revature.planetarium.repository.moon.MoonDaoImp;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.Assert;
import org.junit.rules.ExpectedException;
import java.util.Optional;

public class MoonCreationDaoTest
{
    MoonDaoImp DaoObject;

    String jpgFile = "";
    String webpFile = "";

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

        String jpg = "src\\test\\resources\\Celestial-Images\\moons-1.jpg";
        String webp = "src\\test\\resources\\Celestial-Images\\Testwebp.webp";
        jpgFile = FileEncoder.encoder(jpg);
        webpFile = FileEncoder.encoder(webp);

        DAOTestMoon = new Moon(0, "DAOtest", 1);
        DAOTestMoon.setImageData(jpgFile);
        DAOInvalidName = new Moon(0, "DAOtestMoon!", 1);
        DAOInvalidID = new Moon(0, "DAOBadId", 0);
        DAOBadImage = new Moon(0, "DAOBadImage", 1);
        DAOBadImage.setImageData(webpFile);//Need to find a way to have this be garbage
        Setup.resetTestDatabase();
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
