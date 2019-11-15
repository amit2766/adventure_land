package com.amit.al.entities;

/**
 * Each location block will contain information about one block of map.
 * Map is made up of these location blocks put together. This will be read from JSON and stored here.
 */
public class LocationBlockEntity {
    private String coordinates;
    private String direction;
    private String weapon;
    private String enemy;
    private String food;
    private String location_type;
    private String hint;

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getWeapon() {
        return weapon;
    }

    public void setWeapon(String weapon) {
        this.weapon = weapon;
    }

    public String getEnemy() {
        return enemy;
    }

    public void setEnemy(String enemy) {
        this.enemy = enemy;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public String getLocation_type() {
        return location_type;
    }

    public void setLocationType(String location_type) {
        this.location_type = location_type;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }
}
