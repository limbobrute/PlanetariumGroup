package com.revature.planetarium.repository.planet;

import com.revature.planetarium.entities.Planet;
import com.revature.planetarium.exceptions.PlanetFail;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import java.util.Optional;

public class PlanetDaoPositiveTest {

    private PlanetDao planetDao;
    private Planet positivePlanet;

    @Before
    public void setup() {
        planetDao = new PlanetDaoImp();

        positivePlanet = new Planet();
        positivePlanet.setPlanetName("Earth");
        positivePlanet.setOwnerId(1);
    }

    @Test
    public void createPlanet_validPlanet_returnsPlanetWithAssignedId() {
        Optional<Planet> createdPlanetOpt = planetDao.createPlanet(positivePlanet);

        Assert.assertTrue("Expected non-empty Optional for valid planet", createdPlanetOpt.isPresent());

        Planet createdPlanet = createdPlanetOpt.get();
        Assert.assertTrue("Planet ID should be assigned (non-zero)", createdPlanet.getPlanetId() > 0);

        Assert.assertEquals("Earth", createdPlanet.getPlanetName());
        Assert.assertEquals(1, createdPlanet.getOwnerId());
    }
}
