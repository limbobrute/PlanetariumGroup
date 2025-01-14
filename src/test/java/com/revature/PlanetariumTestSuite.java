package com.revature;

import com.revature.repository.moon.MoonRepositoryDeletionTest;
import com.revature.repository.planet.PlanetRepositoryDeletionTest;
import com.revature.service.moon.MoonServiceDeletionTest;
import com.revature.service.planet.PlanetServiceDeletionTest;
import com.revature.tests.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        MoonRepositoryDeletionTest.class,
        PlanetRepositoryDeletionTest.class,
        MoonServiceDeletionTest.class,
        PlanetServiceDeletionTest.class,
        MoonCreationDaoTest.class,
        MoonCreationServiceTest.class,
        PlanetCreationDaoTest.class,
        PlanetCreationServiceTest.class,
        UserDaoCreationTest.class,
        UserDaoRetrievalTest.class,
        UserServiceAuthentication.class,
        UserServiceCreationTest.class
})
public class PlanetariumTestSuite {
}
