package com.revature.tests.repository.planet;

import com.revature.planetarium.entities.Planet;
import com.revature.planetarium.exceptions.PlanetFail;
import com.revature.planetarium.repository.planet.PlanetDaoImp;
import com.revature.utils.Setup;
import com.revature.utils.FileEncoder;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.Assert;
import org.junit.rules.ExpectedException;
import java.util.Optional;

public class PlanetCreationDaoTest
{
    PlanetDaoImp PlanetDao;
    String jpgFile = "";
    String webpFile = "";

    Planet DAOTestPlanet = new Planet();
    Planet DAOInvalidName = new Planet();
    Planet DAOTooManyCharacters = new Planet();
    Planet DAONonUniqueName = new Planet();
    Planet DAOBadImage = new Planet();


    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setup()
    {
        PlanetDao = new PlanetDaoImp();
        String jpg = "src\\test\\resources\\Celestial-Images\\moons-1.jpg";
        String webp = "src\\test\\resources\\Celestial-Images\\Testwebp.webp";
        jpgFile = FileEncoder.encoder(jpg);
        webpFile = FileEncoder.encoder(webp);

        /*Test Planet data for the Repository Layer*/
        DAOTestPlanet.setPlanetName("DAOTestPlanet");
        DAOTestPlanet.setOwnerId(1);
        DAOInvalidName.setPlanetName("DAOTestPlanet!");
        DAOTooManyCharacters.setPlanetName("DAOTestPlanetwithfartoomanycharacterswhowoulddothis");
        DAONonUniqueName.setPlanetName("Earth");

        DAOTestPlanet.setImageData(jpgFile);
        DAOBadImage.setImageData(webpFile);
        Setup.resetTestDatabase();

    }


    @Test
    public void RepoLayerCreatePlanetSuccess()
    {
        //Looking for Optional object that contains a Planet object with a unique planetID
        Optional<Planet> NewPlanet = PlanetDao.createPlanet(DAOTestPlanet);
        Assert.assertTrue(NewPlanet.isPresent());
    }

    @Test
    public void RepoLayerInvalidPlanetName()
    {
        //Looking for an unhandled PlanetFailException with message "Invalid planet name"
        thrown.expect(PlanetFail.class);
        thrown.expectMessage("Invalid planet name");
        Optional<Planet> NewPlanet = PlanetDao.createPlanet(DAOInvalidName);
    }

    @Test
    public void RepoLayerTooManyCharacters()
    {
        thrown.expect(PlanetFail.class);
        thrown.expectMessage("Invalid planet name");
        Optional<Planet> NewPlanet = PlanetDao.createPlanet(DAOTooManyCharacters);
    }

    @Test
    public void RepoLayerInvalidImagePlanet()
    {
        //Looking for an unhandled PlanetFail Exception with message "Invalid file type"
        thrown.expect(PlanetFail.class);
        Optional<Planet> NewPlanet = PlanetDao.createPlanet(DAOBadImage);
    }
}
