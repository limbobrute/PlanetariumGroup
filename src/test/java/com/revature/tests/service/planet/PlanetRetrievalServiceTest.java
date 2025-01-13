package com.revature.tests.service.planet;

import com.revature.planetarium.entities.Planet;
import com.revature.planetarium.repository.planet.PlanetDao;
import com.revature.planetarium.repository.planet.PlanetDaoImp;
import com.revature.planetarium.service.planet.PlanetService;
import com.revature.planetarium.service.planet.PlanetServiceImp;
import com.revature.utils.Setup;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlanetRetrievalServiceTest {
    PlanetDao PlanetDao;
    PlanetService PlanetService;

    Planet mockExistingPlanet;

    List<Planet> mockPlanetList;
    List<Planet> mockEmptyPlanetList;

    int existingOwnerId;
    int notExistingOwnerId;

    @Before
    public void setup(){
        Setup.resetTestDatabase();
        PlanetDao = Mockito.mock(PlanetDaoImp.class);
        PlanetService = new PlanetServiceImp(PlanetDao);

        mockExistingPlanet = new Planet();

        mockPlanetList = new ArrayList<>(Arrays.asList(mockExistingPlanet));
        mockEmptyPlanetList = new ArrayList<>();

        existingOwnerId = 1;
        notExistingOwnerId = 99;
    }

    @Test
    public void selectByOwnerPositive(){
        Mockito.when(PlanetDao.readPlanetsByOwner(existingOwnerId)).thenReturn(mockPlanetList);
        List<Planet> planets = PlanetService.selectByOwner(existingOwnerId);
        Assert.assertFalse("Returned planet list should not be empty", planets.isEmpty());
    }

    @Test
    public void selectByOwnerNegative(){
        Mockito.when(PlanetDao.readPlanetsByOwner(notExistingOwnerId)).thenReturn(mockEmptyPlanetList);
        List<Planet> planets = PlanetService.selectByOwner(notExistingOwnerId);
        Assert.assertTrue("Returned planet list should be empty", planets.isEmpty());
    }
}