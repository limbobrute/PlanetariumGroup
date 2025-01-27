package com.revature.tests.repository.moon;

import com.revature.planetarium.entities.Moon;
import com.revature.planetarium.exceptions.MoonFail;
import com.revature.utils.Setup;
import com.revature.utils.FileEncoder;
import com.revature.planetarium.repository.moon.MoonDaoImp;
import org.junit.*;
import org.junit.rules.ExpectedException;

import java.io.File;
import java.util.Optional;

public class MoonCreationDaoTest
{
    MoonDaoImp DaoObject;

    String jpgFile = "";
    String webpFile = "";
    String pngFile = "";

    Moon DAOTestMoon;
    Moon DAOInvalidName;
    Moon DAOInvalidID;
    Moon DAOBadImage;
    Moon DAONonUniqueName;
    Moon DAOPngTest;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void Setup()
    {
        DaoObject = new MoonDaoImp();
        String imagePath =  "src" + File.separator +
                            "test" + File.separator +
                            "resources" + File.separator +
                            "Celestial-images" + File.separator;
        String jpg = imagePath + "moons-1.jpg";
        String png = imagePath + "moons-1.png";
        String webp = imagePath + "Testwebp.webp";
        jpgFile = FileEncoder.encoder(jpg);
        webpFile = FileEncoder.encoder(webp);
        pngFile = FileEncoder.encoder(png);

        DAOTestMoon = new Moon(0, "DAOtest", 1);
        DAOTestMoon.setImageData(jpgFile);
        DAOInvalidName = new Moon(0, "DAOtestMoon!", 1);
        DAOInvalidID = new Moon(0, "DAOBadId", 0);
        DAOBadImage = new Moon(0, "DAOBadImage", 1);
        DAOBadImage.setImageData(webpFile);
        DAONonUniqueName = new Moon(0, "Luna", 1);
        DAOPngTest = new Moon(0, "PngTest", 1);
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
        thrown.expectMessage("Invalid moon name");
        Optional<Moon> NewMoon = DaoObject.createMoon(DAOInvalidName);
    }

    @Test
    public void RepoLayerInvalidOwnerId()
    {
        //Looking for unhandled MoonFail Exception with message "Invalid planet ID"
        thrown.expect(MoonFail.class);
        thrown.expectMessage("Invalid planet ID");
        Optional<Moon> NewMoon = DaoObject.createMoon(DAOInvalidID);
    }

    @Test
    public void RepoLayerInvalidMoonImage()
    {
        DAOBadImage.setImageData(webpFile);
        thrown.expect(MoonFail.class);
        thrown.expectMessage("Invalid file type");
        Optional<Moon> NewMoon = DaoObject.createMoon(DAOBadImage);
    }

    @Test
    public void RepoLayerNonUniqueMoonName()
    {
        thrown.expect(MoonFail.class);
        thrown.expectMessage("Invalid moon name");
        Optional<Moon> NewMoon = DaoObject.createMoon(DAONonUniqueName);
    }

    @Test
    public void RepoLayerImageUpload()
    {
        DAOPngTest.setImageData(pngFile);
        Optional<Moon> NewMoon = DaoObject.createMoon(DAOPngTest);
        Assert.assertTrue(NewMoon.isPresent());
    }

    @Test
    public void RepoLayerInvalidImage()
    {
        thrown.expect(MoonFail.class);
        thrown.expectMessage("Invalid file type");
        Optional<Moon> NewMoon = DaoObject.createMoon(DAOBadImage);
    }
}
