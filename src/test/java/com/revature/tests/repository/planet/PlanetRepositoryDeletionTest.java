package com.revature.tests.repository.planet;

import com.revature.planetarium.entities.Moon;
import com.revature.planetarium.exceptions.PlanetFail;
import com.revature.planetarium.repository.moon.MoonDao;
import com.revature.planetarium.repository.moon.MoonDaoImp;
import com.revature.planetarium.repository.planet.PlanetDao;
import com.revature.planetarium.repository.planet.PlanetDaoImp;
import com.revature.utils.Setup;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

public class PlanetRepositoryDeletionTest {

    private PlanetDao planetDao;
    private MoonDao moonDao;
    private String positivePlanet;
    private String negativePlanet;

    @Before
    public void setup() {
        Setup.resetTestDatabase();
        this.planetDao = new PlanetDaoImp();
        this.moonDao = new MoonDaoImp();
        this.positivePlanet = "Earth";
        this.negativePlanet = "NotRealPlanet";
    }

    @Test
    public void daoDeletePlanetNegativeTest() {
        boolean resultDeletePlanet = planetDao.deletePlanet(negativePlanet);
        Assert.assertFalse(resultDeletePlanet);
        PlanetFail thrown = Assert.assertThrows(PlanetFail.class, () -> {
            planetDao.deletePlanet(negativePlanet);
        });

        Assert.assertEquals("Invalid planet name", thrown.getMessage());
    }

    @Test
    public void daoDeletePlanetPositiveTest() {
        boolean resultDeletePlanet = planetDao.deletePlanet(positivePlanet);
        Assert.assertTrue(resultDeletePlanet);
        Optional<Moon> deletedMoon = moonDao.readMoon("Luna");
        Assert.assertTrue(deletedMoon.isEmpty());
    }
}
