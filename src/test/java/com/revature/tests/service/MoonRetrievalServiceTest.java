package com.revature.tests.service;

import com.revature.planetarium.entities.Moon;
import com.revature.planetarium.repository.moon.MoonDao;
import com.revature.planetarium.repository.moon.MoonDaoImp;
import com.revature.planetarium.service.moon.MoonService;
import com.revature.planetarium.service.moon.MoonServiceImp;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class MoonRetrievalServiceTest {
    MoonDao MoonDao;
    MoonService MoonService;
    int existingPlanetId;
    int notExistingPlanetId;

    @Before
    public void setup(){
        MoonDao = new MoonDaoImp();
        MoonService = new MoonServiceImp(MoonDao);
        existingPlanetId = 1;
        notExistingPlanetId = 99;
    }

    @Test
    public void selectByPlanetPositive(){
        List<Moon> moons = MoonService.selectByPlanet(existingPlanetId);
        Assert.assertFalse("Returned moon list should not be empty", moons.isEmpty());
    }

    @Test
    public void selectByPlanetNegative(){
        List<Moon> moons = MoonService.selectByPlanet(notExistingPlanetId);
        Assert.assertTrue("Returned moon list should be empty", moons.isEmpty());
    }
}
