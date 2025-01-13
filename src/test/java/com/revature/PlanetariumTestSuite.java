package com.revature;

import com.revature.repository.moon.MoonRepositoryDeletionTest;
import com.revature.repository.planet.PlanetRepositoryDeletionTest;
import com.revature.service.moon.MoonServiceDeletionTest;
import com.revature.service.planet.PlanetServiceDeletionTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        MoonRepositoryDeletionTest.class,
        PlanetRepositoryDeletionTest.class,
        MoonServiceDeletionTest.class,
        PlanetServiceDeletionTest.class
})
public class PlanetariumTestSuite {

}
