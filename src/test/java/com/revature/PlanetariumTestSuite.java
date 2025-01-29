package com.revature;

import com.revature.tests.repository.moon.MoonRepositoryDeletionTest;
import com.revature.tests.repository.planet.PlanetRepositoryDeletionTest;
import com.revature.tests.repository.planet.PlanetRetrievalRepositoryTest;
import com.revature.tests.service.moon.MoonServiceDeletionTest;
import com.revature.tests.service.planet.PlanetServiceDeletionTest;
import com.revature.tests.repository.moon.MoonCreationDaoTest;
import com.revature.tests.repository.moon.MoonRetrievalRepositoryTest;
import com.revature.tests.repository.planet.PlanetCreationDaoTest;
import com.revature.tests.repository.user.UserDaoCreationTest;
import com.revature.tests.repository.user.UserDaoRetrievalTest;
import com.revature.tests.service.moon.MoonCreationServiceTest;
import com.revature.tests.service.moon.MoonRetrievalServiceTest;
import com.revature.tests.service.planet.PlanetCreationServiceTest;
import com.revature.tests.service.planet.PlanetRetrievalServiceTest;
import com.revature.tests.service.user.UserServiceAuthentication;
import com.revature.tests.service.user.UserServiceCreationTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        // Gerald
        UserDaoCreationTest.class,
        UserDaoRetrievalTest.class,
        UserServiceAuthentication.class,
        UserServiceCreationTest.class,
        // John Paul
        PlanetRetrievalServiceTest.class,
        MoonRetrievalRepositoryTest.class,
        PlanetRetrievalRepositoryTest.class,
        MoonRetrievalServiceTest.class,
        // William
        PlanetCreationDaoTest.class,
        MoonCreationDaoTest.class,
        PlanetCreationServiceTest.class,
        MoonCreationServiceTest.class,
        // Mick
        PlanetRepositoryDeletionTest.class,
        MoonRepositoryDeletionTest.class,
        PlanetServiceDeletionTest.class,
        MoonServiceDeletionTest.class,

})
public class PlanetariumTestSuite {
//test
}