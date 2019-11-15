package com.amit.al.map;

import com.amit.al.entities.Coordinates;
import com.amit.al.entities.FoodEntity;
import com.amit.al.entities.WeaponEntity;
import com.amit.al.enemy.Enemy;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * One location coordinate can have maximum of one enemy, food , weapon and location type
 */
public class GameMap {

    private final int level;
    private Map<Coordinates, FoodEntity> foodLocation = new HashMap<>();
    private Map<Coordinates, WeaponEntity> weaponLocation = new HashMap<>();
    private Map<Coordinates, Enemy> enemyLocation = new HashMap<>();
    private Map<Coordinates, LocationType> locationType = new HashMap<>();
    private Map<Coordinates, String> locationHint = new HashMap<>();
    private Map<Coordinates, String> directionString = new HashMap<>();

    private Set<Coordinates> coordinates = new HashSet<>();

    public GameMap(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public Map<Coordinates, FoodEntity> getFoodLocation() {
        return foodLocation;
    }

    public Map<Coordinates, WeaponEntity> getWeaponLocation() {
        return weaponLocation;
    }

    public Map<Coordinates, Enemy> getEnemyLocation() {
        return enemyLocation;
    }


    public Map<Coordinates, LocationType> getLocationType() {
        return locationType;
    }

    public Map<Coordinates, String> getLocationHint() {
        return locationHint;
    }

    public Map<Coordinates, String> getDirectionString() {
        return directionString;
    }

    public Set<Coordinates> getCoordinates() {
        return coordinates;
    }
}
