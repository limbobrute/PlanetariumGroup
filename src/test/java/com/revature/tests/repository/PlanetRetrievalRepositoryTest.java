package com.revature.tests.repository;

import com.revature.planetarium.entities.Planet;
import com.revature.planetarium.repository.planet.PlanetDao;
import com.revature.planetarium.repository.planet.PlanetDaoImp;
import com.revature.utils.Setup;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class PlanetRetrievalRepositoryTest {
    PlanetDao PlanetDao;
    int existingOwnerId;
    int notExistingOwnerId;

    @Before
    public void setup(){
        Setup.resetTestDatabase();
        PlanetDao = new PlanetDaoImp();
        existingOwnerId = 1;
        notExistingOwnerId = 99;
    }

    @Test
    public void readPlanetsByOwnerPositive(){
        List<Planet> planets = PlanetDao.readPlanetsByOwner(existingOwnerId);
        Assert.assertFalse("Returned planet list should not be empty", planets.isEmpty());
    }

    @Test
    public void readPlanetsByOwnerNegative(){
        List<Planet> planets = PlanetDao.readPlanetsByOwner(notExistingOwnerId);
        Assert.assertTrue("Returned planet list should be empty", planets.isEmpty());
    }
}
