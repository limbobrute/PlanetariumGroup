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
        //repository/moon
        MoonCreationDaoTest.class,
        MoonRepositoryDeletionTest.class,
        MoonRetrievalRepositoryTest.class,

        //repository/planet
        PlanetCreationDaoTest.class,
        PlanetRepositoryDeletionTest.class,
        PlanetRetrievalRepositoryTest.class,

        //repository/user
        UserDaoCreationTest.class,
        UserDaoRetrievalTest.class,

        //service/moon
        MoonCreationServiceTest.class,
        MoonRetrievalServiceTest.class,
        MoonServiceDeletionTest.class,

        //service/planet
        PlanetCreationServiceTest.class,
        PlanetRetrievalServiceTest.class,
        PlanetServiceDeletionTest.class,

        //service/user
        UserServiceAuthentication.class,
        UserServiceCreationTest.class

})
public class PlanetariumTestSuite {
//jenkins push 
}