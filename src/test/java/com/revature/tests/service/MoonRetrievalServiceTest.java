package com.revature.tests.service;

import com.revature.planetarium.entities.Moon;
import com.revature.planetarium.repository.moon.MoonDao;
import com.revature.planetarium.repository.moon.MoonDaoImp;
import com.revature.planetarium.service.moon.MoonService;
import com.revature.planetarium.service.moon.MoonServiceImp;
import com.revature.utils.Setup;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MoonRetrievalServiceTest {
    MoonDao MoonDao;
    MoonService MoonService;

    Moon mockExistingMoon;

    List<Moon> mockMoonList;
    List<Moon> mockEmptyMoonList;

    int existingPlanetId;
    int notExistingPlanetId;

    @Before
    public void setup(){
        Setup.resetTestDatabase();
        MoonDao = Mockito.mock(MoonDaoImp.class);
        MoonService = new MoonServiceImp(MoonDao);

        mockExistingMoon = new Moon();

        mockMoonList = new ArrayList<>(Arrays.asList(mockExistingMoon));
        mockEmptyMoonList = new ArrayList<>();

        existingPlanetId = 1;
        notExistingPlanetId = 99;
    }

    @Test
    public void selectByPlanetPositive(){
        Mockito.when(MoonDao.readMoonsByPlanet(existingPlanetId)).thenReturn(mockMoonList);
        List<Moon> moons = MoonService.selectByPlanet(existingPlanetId);
        Assert.assertFalse("Returned moon list should not be empty", moons.isEmpty());
    }

    @Test
    public void selectByPlanetNegative(){
        Mockito.when(MoonDao.readMoonsByPlanet(notExistingPlanetId)).thenReturn(mockEmptyMoonList);
        List<Moon> moons = MoonService.selectByPlanet(notExistingPlanetId);
        Assert.assertTrue("Returned moon list should be empty", moons.isEmpty());
    }
}
