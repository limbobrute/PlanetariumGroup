package com.revature.planetarium.service.moon;

import com.revature.planetarium.entities.Moon;
import com.revature.planetarium.entities.Planet;
import com.revature.planetarium.exceptions.MoonFail;
import com.revature.planetarium.repository.moon.MoonDao;
import com.revature.planetarium.repository.planet.PlanetDao;
import com.revature.planetarium.repository.planet.PlanetDaoImp;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MoonServiceImp<T> implements MoonService<T> {
    
    private MoonDao moonDao;
    private PlanetDao planetDao;
    public MoonServiceImp(MoonDao moonDao) {
        this.moonDao = moonDao;
        planetDao = new PlanetDaoImp();
    }

    @Override
    public Moon createMoon(Moon moon) {
        Pattern pattern = Pattern.compile("[^a-zA-Z0-9\\s\\-_]");
        Matcher matcher = pattern.matcher(moon.getMoonName());
        byte[] arr = moon.imageDataAsByteArray();

        if(arr != null && arr[0] != (byte) 0x89 && arr[0] != (byte) 0xFF)
        { throw new MoonFail("Invalid file type");}

        if(matcher.find()) {
            throw new MoonFail("Invalid moon name");
        }

        if (moon.getMoonName().length() < 1 || moon.getMoonName().length() > 30) {
            throw new MoonFail("Invalid moon name");
        }
        Optional<Moon> existingMoon = moonDao.readMoon(moon.getMoonName());
        if (existingMoon.isPresent()) {
            throw new MoonFail("Invalid moon name");
        }

        Optional<Planet> existingPlanet = planetDao.readPlanet(moon.getOwnerId());
        if(existingPlanet.isEmpty()){
            throw new MoonFail("Invalid planet id");
        }
        Optional<Moon> newMoon = moonDao.createMoon(moon);
        if (newMoon.isEmpty()) {
            throw new MoonFail("Could not create new moon");
        }
        return newMoon.get();
    }


    @Override
    public Moon selectMoon(T idOrName) {
        Optional<Moon> moon;
        if (idOrName instanceof Integer) {
            moon = moonDao.readMoon((Integer) idOrName);
        } else if (idOrName instanceof String) {
            moon = moonDao.readMoon((String) idOrName);
        } else {
            throw new MoonFail("Identifier must be an Integer or String");
        }
        if(moon.isPresent()) {
            return moon.get();
        } else {
            throw new MoonFail("Moon not found");
        }
    }

    @Override
    public List<Moon> selectAllMoons() {
        return moonDao.readAllMoons();
    }

    @Override
    public List<Moon> selectByPlanet(int planetId) {
        return moonDao.readMoonsByPlanet(planetId);
    }

    @Override
    public Moon updateMoon(Moon moon) {
        Optional<Moon> existingMoon = moonDao.readMoon(moon.getMoonId());
        if (existingMoon.isEmpty()) {
            throw new MoonFail("Moon not found, could not update");
        }
        if (moon.getMoonName().length() < 1 || moon.getMoonName().length() > 30) {
            throw new MoonFail("Moon name must be between 1 and 30 characters, could not update");
        }
        Optional<Moon> moonWithSameName = moonDao.readMoon(moon.getMoonName());
        if (moonWithSameName.isPresent() && moonWithSameName.get().getMoonId() != moon.getMoonId()) {
            throw new MoonFail("Moon name must be unique, could not update");
        }
        Optional<Moon> updatedMoon = moonDao.updateMoon(moon);
        if (updatedMoon.isPresent()) {
            return updatedMoon.get();
        } else {
            throw new MoonFail("Moon update failed, please try again");
        }
    }

    @Override
    public boolean deleteMoon(T idOrName) {
        boolean deleted;
        if (idOrName instanceof Integer) {
            deleted = moonDao.deleteMoon((int) idOrName);
        } else if (idOrName instanceof String) {
            deleted = moonDao.deleteMoon((String) idOrName);
        } else {
            throw new MoonFail("Identifier must be an Integer or String");
        }
        if (deleted) {
            return true;
        } else {
            throw new MoonFail("Invalid moon name");
        }
    }
}