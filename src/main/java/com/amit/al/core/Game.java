package com.amit.al.core;

import com.amit.al.commands.CommandFactory;
import com.amit.al.commands.CommandParser;
import com.amit.al.commands.CommandResult;
import com.amit.al.core.player.Player;
import com.amit.al.entities.FoodEntity;
import com.amit.al.entities.WeaponEntity;
import com.amit.al.entities.EnemyEntity;
import com.amit.al.map.GameMap;
import com.amit.al.repository.MapRepository;
import com.amit.al.utilities.CustomFileReader;
import com.amit.al.utilities.UserInteraction;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static com.amit.al.utilities.Constants.*;

/**
 * This is the game instance. It is singleton and should always be present until game exits.
 * Game will contain game map and player. Both game map and player are singleton as well.
 */
public class Game {

    private static Game game;
    private UserInteraction userInteraction = UserInteraction.getInstance();
    private Player player;
    private CommandParser commandParser = new CommandParser();
    private GameMap gameMap;
    private ArrayList<EnemyEntity> enemiesListFromData;
    private ArrayList<FoodEntity> foodListFromData;
    private ArrayList<WeaponEntity> weaponsListFromData;

    private Game() {
    }

    public static Game getInstance() {
        if (game == null) {
            game = new Game();
            game.loadDataFiles();
        }
        return game;
    }

    private void loadDataFiles() {
        enemiesListFromData = createEnemiesListFromData();
        foodListFromData = createFoodListFromData();
        weaponsListFromData = createWeaponsListFromData();
        if (enemiesListFromData == null || foodListFromData == null || weaponsListFromData == null) {
            userInteraction.showMessageToUserln("Unable to read data files something went wrong. Exiting.");
            System.exit(-1);
        }
    }

    public void startNewGame() {
        this.player = Player.createNewPlayer();
        introduceGame();
        userInteraction.showMessageToUserln(introducePlayer(player));
        GameMap gameMap = loadMap(false);
        this.gameMap = gameMap;
        saveGame();
        userInteraction.loadedSuccessful("game");
    }


    public void resumeOldGame() {
        this.player = Player.loadOldPlayer();
        GameMap gameMap = loadMap(true);
        this.gameMap = gameMap;
        userInteraction.loadedSuccessful("game");

    }

    private GameMap loadMap(boolean isOldMap) {
        MapRepository loadMap = new MapRepository();
        CommandResult result = loadMap.load(isOldMap, player.getLevel());
        if (CommandResult.SUCCESS != result) {
            userInteraction.showMessageToUserln("Map loading failed. You have to quit the game.");
        }
        return loadMap.getGameMap();
    }


    public Player getPlayer() {
        return player;
    }

    public CommandResult saveGame() {
        return new CommandFactory().getCommand("save", "save").execute();
    }

    private String introducePlayer(Player player) {
        String playerName = (player.getProfile()).getName();
        return "Hello " + playerName + ", I am here to guide you, just listen to me and everything will be alright.";
    }

    private void introduceGame() {
        try {
            BufferedReader br = CustomFileReader.readFileFromResourcesAsBR("gamedata/game_intro.txt");
            String line;
            while ((line = br.readLine()) != null) {
                userInteraction.showMessageToUserln(line);
            }
        } catch (IOException e) {
            userInteraction.showMessageToUser("Unable to read map", UserInteraction.ANSI_RED);
        }


    }

    /**
     * Takes command unless game is exiting
     */
    public void gameCommands() {
        boolean keepPlaying = true;
        while (keepPlaying) {
            userInteraction.newLine();
            userInteraction.showMessageToUserln("Command me something. Type 'help' to take help.\n");
            String userCommand = userInteraction.takeInputsFromUser();
            CommandResult commandResult = commandParser.parse(userCommand);
            if (commandResult.equals(CommandResult.FAILED)) {
                userInteraction.showMessageToUserln("Something went wrong with command it got failed");
            }
            if (commandResult.equals(CommandResult.EXIT_GAME)) {
                userInteraction.showMessageToUserln("Exiting the game good bye");
                keepPlaying = false;
            }
        }

    }

    public GameMap getGameMap() {
        return gameMap;
    }

    public ArrayList<EnemyEntity> getEnemiesListFromData() {
        return enemiesListFromData;
    }

    public ArrayList<FoodEntity> getFoodListFromData() {
        return foodListFromData;
    }

    public ArrayList<WeaponEntity> getWeaponsListFromData() {
        return weaponsListFromData;
    }

    private ArrayList<WeaponEntity> createWeaponsListFromData() {
        try {
            BufferedReader br = CustomFileReader.readFileFromResourcesAsBR(WEAPON_FILE_NAME);
            Type type = new TypeToken<List<WeaponEntity>>() {
            }.getType();
            return new Gson().fromJson(br, type);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private ArrayList<FoodEntity> createFoodListFromData() {
        try {
            BufferedReader br = CustomFileReader.readFileFromResourcesAsBR(FOOD_FILE_NAME);
            Type type = new TypeToken<List<FoodEntity>>() {
            }.getType();
            return new Gson().fromJson(br, type);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private ArrayList<EnemyEntity> createEnemiesListFromData() {
        try {
            BufferedReader br = CustomFileReader.readFileFromResourcesAsBR(ENEMY_FILE_NAME);
            Type type = new TypeToken<List<EnemyEntity>>() {
            }.getType();
            return new Gson().fromJson(br, type);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
