package com.revature.tests.repository;

import com.revature.planetarium.entities.Moon;
import com.revature.planetarium.repository.moon.MoonDao;
import com.revature.planetarium.repository.moon.MoonDaoImp;
import com.revature.utils.Setup;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class MoonRetrievalRepositoryTest {
    MoonDao MoonDao;
    int existingPlanetId;
    int notExistingPlanetId;
    @Before
    public void setup(){
        Setup.resetTestDatabase();
        MoonDao = new MoonDaoImp();
        existingPlanetId = 1;
        notExistingPlanetId = 99;
    }

    @Test
    public void readMoonsByPlanetPositive(){
        List<Moon> moons = MoonDao.readMoonsByPlanet(existingPlanetId);
        Assert.assertFalse("Returned moon list should not be empty", moons.isEmpty());
    }

    @Test
    public void readMoonsByPlanetNegative(){
        List<Moon> moons = MoonDao.readMoonsByPlanet(notExistingPlanetId);
        Assert.assertTrue("Returned moon list should be empty", moons.isEmpty());
    }
}
