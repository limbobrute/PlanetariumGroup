package com.revature.repository.moon;

import com.revature.planetarium.exceptions.MoonFail;
import com.revature.planetarium.repository.moon.MoonDao;

import com.revature.planetarium.repository.moon.MoonDaoImp;
import com.revature.utils.Setup;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MoonDeletionTest {

    private MoonDao moonDao;
    private String positiveMoon;
    private String negativeMoon;

    @Before
    public void setup() {
        Setup.resetTestDatabase();
        this.moonDao = new MoonDaoImp();
        this.positiveMoon = "Luna";
        this.negativeMoon = "NotRealMoon";
    }

    @Test
    public void daoDeleteMoonNegativeTest() {
        boolean resultDeleteMoon = moonDao.deleteMoon(negativeMoon);
        Assert.assertFalse(resultDeleteMoon);
        MoonFail thrown = Assert.assertThrows(MoonFail.class, () -> {
            moonDao.deleteMoon(negativeMoon);
        });

        Assert.assertEquals("Invalid moon name", thrown.getMessage());
    }


    @Test
    public void daoDeleteMoonPositiveTest() {
        boolean resultDeleteMoon = moonDao.deleteMoon(positiveMoon);
        Assert.assertTrue(resultDeleteMoon);
    }
}
