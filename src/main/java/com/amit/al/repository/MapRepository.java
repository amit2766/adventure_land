package com.amit.al.repository;

import com.amit.al.UserInteraction;
import com.amit.al.commands.CommandResult;
import com.amit.al.core.Game;
import com.amit.al.entities.Coordinates;
import com.amit.al.entities.FoodEntity;
import com.amit.al.entities.LocationBlockEntity;
import com.amit.al.entities.WeaponEntity;
import com.amit.al.enemy.Enemy;
import com.amit.al.entities.EnemyEntity;
import com.amit.al.map.GameMap;
import com.amit.al.map.LocationType;
import com.amit.al.utilities.Constants;
import com.amit.al.utilities.CustomFileReader;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * This is persistence layer. Here Objects will be saved to json and will be read from json for Map.
 */
public class MapRepository {

    private UserInteraction userInteraction = UserInteraction.getInstance();
    private GameMap gameMap;

    public void saveMap(String mapName, Game game) throws IOException {
        List<LocationBlockEntity> blocks = new ArrayList<>();

        GameMap gameMap = game.getGameMap();
        for (Coordinates coordinates :
                gameMap.getCoordinates()) {
            LocationBlockEntity block = new LocationBlockEntity();

            block.setCoordinates(coordinates.toString());
            block.setDirection(gameMap.getDirectionString().get(coordinates));
            String hint = gameMap.getLocationHint().get(coordinates);
            if (hint != null) {
                block.setHint(hint);
            } else {
                block.setHint("none");
            }
            WeaponEntity weaponEntity = gameMap.getWeaponLocation().get(coordinates);
            if (weaponEntity != null) {
                block.setWeapon(weaponEntity.getName());
            } else {
                block.setWeapon("none");
            }
            Enemy enemy = gameMap.getEnemyLocation().get(coordinates);
            if (enemy != null) {
                block.setEnemy(enemy.getName());
            } else {
                block.setEnemy("none");
            }
            FoodEntity foodEntity = gameMap.getFoodLocation().get(coordinates);
            if (foodEntity != null) {
                block.setFood(foodEntity.getName());
            } else {
                block.setFood("none");
            }
            block.setLocationType(gameMap.getLocationType().get(coordinates).name());

            blocks.add(block);
        }

        Gson gson = new Gson();
        Writer writer = new FileWriter(mapName);
        gson.toJson(blocks, writer);
        writer.flush();
        writer.close();
    }

    public CommandResult load(boolean isOldMap, int playerLevel) {
        userInteraction.showMessageToUserln("Loading map...");
        BufferedReader br;
        if (isOldMap) {
            try {
                br = CustomFileReader.readFileFromLocation(Constants.MAP_NAME);
            } catch (FileNotFoundException e) {
                return CommandResult.FAILED;
            }
        } else {
            try {
                String fileName = selectMapDataFile(playerLevel);
                br = CustomFileReader.readFileFromResourcesAsBR(fileName);
            } catch (IOException e) {
                e.printStackTrace();
                return CommandResult.FAILED;
            }
        }

        Type type = new TypeToken<List<LocationBlockEntity>>() {
        }.getType();
        ArrayList<LocationBlockEntity> locationBlock = new Gson().fromJson(br, type);
        gameMap = new GameMap(playerLevel);
        return createMap(locationBlock);
    }


    /**
     * Converts location block array to Map Object. Each map will contain item, enemy etc.
     *
     * @param locationBlocks location block. Map is made up of many location blocks. This enables map to have
     *                       any possible shape, size.
     */
    private CommandResult createMap(ArrayList<LocationBlockEntity> locationBlocks) {
        Game game = Game.getInstance();
        ArrayList<FoodEntity> foods = game.getFoodListFromData();
        ArrayList<EnemyEntity> enemies = game.getEnemiesListFromData();
        ArrayList<WeaponEntity> weapons = game.getWeaponsListFromData();

        for (LocationBlockEntity block :
                locationBlocks) {
            Coordinates coordinates = new Coordinates(block.getCoordinates());
            gameMap.getCoordinates().add(coordinates);

            if (!"none".equalsIgnoreCase(block.getFood())) {
                FoodEntity food = createFood(foods, block.getFood().toLowerCase());
                gameMap.getFoodLocation().put(coordinates, food);
            }
            if (!"none".equalsIgnoreCase(block.getEnemy())) {
                Enemy enemy = createEnemy(enemies, block.getEnemy().toLowerCase());
                gameMap.getEnemyLocation().put(coordinates, enemy);
            }
            if (!"none".equalsIgnoreCase(block.getWeapon())) {
                WeaponEntity weapon = createWeapon(weapons, block.getWeapon().toLowerCase());
                gameMap.getWeaponLocation().put(coordinates, weapon);
            }
            if (!"none".equalsIgnoreCase(block.getDirection())) {
                gameMap.getDirectionString().put(coordinates, block.getDirection());
            }
            if (!"none".equalsIgnoreCase(block.getHint())) {
                gameMap.getLocationHint().put(coordinates, block.getHint());
            }
            if (!"none".equalsIgnoreCase(block.getLocation_type())) {
                gameMap.getLocationType().put(coordinates, LocationType.valueOf(block.getLocation_type().toUpperCase()));
            }
        }
        return CommandResult.SUCCESS;
    }

    private WeaponEntity createWeapon(ArrayList<WeaponEntity> weapons, String weaponName) {
        for (WeaponEntity weapon : weapons) {
            if (weaponName.equalsIgnoreCase(weapon.getName()))
                return weapon;
        }
        return null;
    }

    private Enemy createEnemy(ArrayList<EnemyEntity> enemies, String enemyName) {
        for (EnemyEntity entity : enemies) {
            if (enemyName.equalsIgnoreCase(entity.getName())) {
                return (Enemy) entity.toEnemy(new Enemy());
            }
        }
        return null;
    }

    private FoodEntity createFood(ArrayList<FoodEntity> foods, String foodName) {
        for (FoodEntity food : foods) {
            if (foodName.equalsIgnoreCase(food.getName())) {
                return food;
            }
        }
        return null;
    }

    private String selectMapDataFile(int playerLevel) {
        switch (playerLevel) {
            case 1:
                return "gamedata/maps/level1map.data.json";
            case 2:
                return "gamedata/maps/level2map.data.json";
            case 3:
                return "gamedata/maps/level3map.data.json";
            default:
                return null;
        }
    }

    public GameMap getGameMap() {
        return gameMap;
    }
}
