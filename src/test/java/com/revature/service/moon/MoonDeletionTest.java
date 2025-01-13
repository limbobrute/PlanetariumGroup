package com.revature.service.moon;

import com.revature.planetarium.entities.Moon;
import com.revature.planetarium.exceptions.MoonFail;
import com.revature.planetarium.repository.moon.MoonDao;
import com.revature.planetarium.service.moon.MoonService;
import com.revature.planetarium.service.moon.MoonServiceImp;
import com.revature.utils.Setup;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class MoonDeletionTest {

    private MoonDao moonDao;
    private MoonService moonService;
    private String positiveMoon;
    private String negativeMoon;
    @Before
    public void setup() {
        Setup.resetTestDatabase();
        moonDao = Mockito.mock(MoonDao.class);
        moonService = new MoonServiceImp(moonDao);
        positiveMoon = "Luna";
        negativeMoon = "NotRealMoon";
    }

    @Test
    public void serviceDeleteMoonPositiveTest() {
        Mockito.when(moonDao.deleteMoon(positiveMoon)).thenReturn(true);
        Object positiveResult = moonService.deleteMoon(positiveMoon);
        Assert.assertTrue(positiveResult instanceof Boolean);
    }

    @Test
    public void serviceDeleteMoonNegativeTest() {
        Mockito.when(moonDao.deleteMoon(negativeMoon)).thenThrow(new MoonFail("Invalid moon name"));
        MoonFail thrown = Assert.assertThrows(MoonFail.class, () -> {
            moonService.deleteMoon(negativeMoon);
        });
        Assert.assertEquals("Invalid moon name", thrown.getMessage());
    }
}
