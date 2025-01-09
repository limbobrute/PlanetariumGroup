package com.revature.tests;

import com.revature.planetarium.entities.Planet;
import com.revature.planetarium.exceptions.PlanetFail;
import com.revature.planetarium.repository.planet.PlanetDaoImp;
import com.revature.planetarium.service.planet.PlanetServiceImp;
import com.revature.utils.Setup;
import com.revature.utils.FileEncoder;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.Assert;
import org.junit.rules.ExpectedException;

import java.util.Optional;

public class PlanetCreationTest
{
    PlanetDaoImp PlanetDao;
    PlanetServiceImp PlanetService;
    String jpgFile = "";
    String webpFile = "";

    /*Test Planet data for the Service Layer*/
    Planet ServiceTestPlanet = new Planet();
    Planet ServiceInvalidName = new Planet();
    Planet ServiceTooManyCharacters = new Planet();
    Planet ServiceNonUniqueName = new Planet();
    Planet ServiceBadImage = new Planet();

    /*Test Planet data for the Repository Layer*/
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
        PlanetService = new PlanetServiceImp(PlanetDao);
        String jpg = "src\\test\\resources\\Celestial-Images\\moons-1.jpg";
        String webp = "src\\test\\resources\\Celestial-Images\\Testwebp.webp";

        /*Test Planet data for the Service Layer*/
        ServiceTestPlanet.setPlanetName("ServiceTestPlanet");
        ServiceInvalidName.setPlanetName("ServiceTestPlanet!");
        ServiceTooManyCharacters.setPlanetName("ServiceTestPlanetwithfartoomanycharacterswhowoulddothis");
        ServiceNonUniqueName.setPlanetName("Titan");

        /*Test Planet data for the Repository Layer*/
        DAOTestPlanet.setPlanetName("DAOTestPlanet");
        DAOInvalidName.setPlanetName("DAOTestPlanet!");
        DAOTooManyCharacters.setPlanetName("DAOTestPlanetwithfartoomanycharacterswhowoulddothis");
        DAONonUniqueName.setPlanetName("Earth");

        DAOTestPlanet.setImageData(jpgFile);
        DAOBadImage.setImageData(webpFile);
        Setup.resetTestDatabase();

    }

    //Service Layer Tests for Planet Creation
    @Test
    public void ServiceLayerCreatePlanetSuccess()
    {
        //Looking for boolean primitive set to TRUE
        Planet NewPlanet = PlanetService.createPlanet(ServiceTestPlanet);
        Assert.assertEquals(2, NewPlanet.getPlanetId());
    }

    @Test
    public void ServiceLayerNonUniquePlanetName()
    {
        //Looking for unhandled PlanetFail Exception with message "Invalid planet name"
        thrown.expect(PlanetFail.class);
        thrown.expectMessage("unique name fail");
        Planet NewPlanet = PlanetService.createPlanet(ServiceTestPlanet);
    }

    @Test
    public void ServiceLayerInvalidPlanetName()
    {
        thrown.expect(PlanetFail.class);
        //thrown.expectMessage("Invalid characters");
        Planet NewPlanet = PlanetService.createPlanet(ServiceInvalidName);
    }

    @Test
    public void ServiceLayerTooManyCharacters()
    {
        thrown.expect(PlanetFail.class);
        thrown.expectMessage("character length fail");
        Planet NewPlanet = PlanetService.createPlanet(ServiceTooManyCharacters);
    }

    @Test
    public void ServiceLayerInvalidImagePlanet()
    {
        //Looking for unhandled PlanetFail Exception with message "Invalid file type"
    }


    //Repository Layer Tests
    @Test
    public void RepoLayerCreatePlanetSuccess()
    {
        //Looking for Optional object that contains a Planet object with a unique planetID
        Optional<Planet> NewPlanet = PlanetDao.createPlanet(DAOTestPlanet);
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
