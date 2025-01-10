package com.revature.tests;

import com.revature.planetarium.entities.Planet;
import com.revature.planetarium.exceptions.PlanetFail;
import com.revature.planetarium.service.planet.PlanetServiceImp;
import com.revature.planetarium.repository.planet.PlanetDaoImp;
import com.revature.utils.Setup;
import com.revature.utils.FileEncoder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

public class PlanetCreationServiceTest
{
    PlanetDaoImp PlanetDao;
    PlanetServiceImp PlanetService;
    String jpgFile = "";
    String webpFile = "";

    Planet ServiceTestPlanet = new Planet();
    Planet ServiceInvalidName = new Planet();
    Planet ServiceTooManyCharacters = new Planet();
    Planet ServiceNonUniqueName = new Planet();
    Planet ServiceBadImage = new Planet();

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setup()
    {
        PlanetDao = Mockito.mock(PlanetDaoImp.class);
        PlanetService = new PlanetServiceImp(PlanetDao);

        String jpg = "src\\test\\resources\\Celestial-Images\\moons-1.jpg";
        String webp = "src\\test\\resources\\Celestial-Images\\Testwebp.webp";
        jpgFile = FileEncoder.encoder(jpg);
        webpFile = FileEncoder.encoder(webp);

        ServiceTestPlanet.setPlanetName("ServiceTestPlanet");
        ServiceTestPlanet.setOwnerId(1);
        ServiceInvalidName.setPlanetName("ServiceTestPlanet!");
        ServiceTooManyCharacters.setPlanetName("ServiceTestPlanetwithfartoomanycharacterswhowoulddothis");
        ServiceNonUniqueName.setPlanetName("Titan");

        ServiceTestPlanet.setImageData(jpgFile);
        ServiceBadImage.setImageData(webpFile);

        Setup.resetTestDatabase();
    }

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
        thrown.expectMessage("Invalid characters");
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
        thrown.expect(PlanetFail.class);
        thrown.expectMessage("Invalid file type");
        Planet NewPlanet = PlanetService.createPlanet(ServiceBadImage);
    }
}
