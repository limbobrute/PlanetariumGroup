package com.revature.tests;

import com.revature.planetarium.entities.Planet;
import com.revature.planetarium.repository.planet.PlanetDaoImp;
import com.revature.planetarium.service.planet.PlanetServiceImp;
import org.junit.Test;
import org.junit.Assert;

public class PlanetCreationTest
{
    Planet TestPlanet;

    //Service Layer Tests for Planet Creation
    @Test
    public void ServiceLayerCreatePlanetSuccess()
    {
        //Looking for boolean primitive set to TRUE
    }

    @Test
    public void ServiceLayerInvalidPlanetName()
    {
        //Looking for unhandled PlanetFail Exception with message "Invalid planet name"
    }

    @Test
    public void ServiceLayerInvalidImagePlanet()
    {
        //Looking for unhandled PlanetFail Exception with message "Invalid file type"
    }


    //Repository Layer Tests
    @Test
    public void RepoLayerCreatePlanetSuccess()
    {
        //Looking for Optional object that contains a Planet object with a unique planetID
    }

    @Test
    public void RepoLayerInvalidPlanetName()
    {
        //Looking for an unhandled PlanetFailException with message "Invalid planet name"
    }

    @Test
    public void RepoLayerInvalidImagePlanet()
    {
        //Looking for an unhandled PlanetFail Exception with message "Invalid file type"
    }
}
