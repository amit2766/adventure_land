package com.amit.al.commands;

import com.amit.al.UserInteraction;
import com.amit.al.core.Game;
import com.amit.al.core.player.Player;
import com.amit.al.entities.Coordinates;
import com.amit.al.entities.FoodEntity;
import com.amit.al.map.GameMap;
public class EatFoodCommand implements Command {
    @Override
    public CommandResult execute() {
        Player player = Player.getInstance();
        Coordinates location = player.getLocation();
        GameMap gameMap = Game.getInstance().getGameMap();
        FoodEntity foodEntity = gameMap.getFoodLocation().get(location);
        if (foodEntity != null) {
            int health = player.getProfile().getHealth();
            int newHealth = health + foodEntity.getHealth();
            player.getProfile().setHealth(newHealth);
            gameMap.getFoodLocation().remove(location);
            gameMap.getLocationHint().replace(location, "none");
            UserInteraction.getInstance().showMessageToUserln("Health improved from " + health + " to " + newHealth);
            return CommandResult.SUCCESS;
        } else {
            UserInteraction.getInstance().showMessageToUserln("There is nothing to eat here. Do you eat dirt?");
            return CommandResult.NOT_EXECUTED;
        }
    }
}
