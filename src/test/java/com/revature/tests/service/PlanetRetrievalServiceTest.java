package com.revature.tests.service;

import com.revature.planetarium.entities.Planet;
import com.revature.planetarium.repository.planet.PlanetDao;
import com.revature.planetarium.repository.planet.PlanetDaoImp;
import com.revature.planetarium.service.planet.PlanetService;
import com.revature.planetarium.service.planet.PlanetServiceImp;
import com.revature.utils.Setup;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class PlanetRetrievalServiceTest {
    PlanetDao PlanetDao;
    PlanetService PlanetService;
    int existingOwnerId;
    int notExistingOwnerId;

    @Before
    public void setup(){
        Setup.resetTestDatabase();
        PlanetDao = new PlanetDaoImp();
        PlanetService = new PlanetServiceImp(PlanetDao);
        existingOwnerId = 1;
        notExistingOwnerId = 99;
    }

    @Test
    public void selectByOwnerPositive(){
        List<Planet> planets = PlanetService.selectByOwner(existingOwnerId);
        Assert.assertFalse("Returned planet list should not be empty", planets.isEmpty());
    }

    @Test
    public void selectByOwnerNegative(){
        List<Planet> planets = PlanetService.selectByOwner(notExistingOwnerId);
        Assert.assertTrue("Returned planet list should be empty", planets.isEmpty());
    }
}