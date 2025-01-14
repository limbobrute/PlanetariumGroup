package com.revature.tests.service.planet;

import com.revature.planetarium.exceptions.PlanetFail;
import com.revature.planetarium.repository.planet.PlanetDao;
import com.revature.planetarium.repository.planet.PlanetDaoImp;
import com.revature.planetarium.service.planet.PlanetService;
import com.revature.planetarium.service.planet.PlanetServiceImp;
import com.revature.utils.Setup;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class PlanetServiceDeletionTest {

    private PlanetDao planetDao;
    private PlanetService planetService;
    private String positivePlanet;
    private String negativePlanet;
    @Before
    public void setup() {
        Setup.resetTestDatabase();
        planetDao = Mockito.mock(PlanetDaoImp.class);
        planetService = new PlanetServiceImp(planetDao);
        positivePlanet = "Earth";
        negativePlanet = "NotRealPlanet";
    }

    @Test
    public void serviceDeletePlanetPositiveTest() {
        Mockito.when(planetDao.deletePlanet(positivePlanet)).thenReturn(true);
        Object positiveResult = planetService.deletePlanet(positivePlanet);
        if (positiveResult instanceof String) {
            Assert.assertEquals("true", positiveResult);
        } else {
            Assert.fail("positiveResult is not a String");
        }
    }

    @Test
    public void serviceDeletePlanetNegativeTest() {
        Mockito.when(planetDao.deletePlanet(negativePlanet)).thenThrow(new PlanetFail("Invalid planet name"));
        PlanetFail thrown = Assert.assertThrows(PlanetFail.class, () -> {
            planetService.deletePlanet(negativePlanet);
        });
        Assert.assertEquals("Invalid planet name", thrown.getMessage());
    }
}
