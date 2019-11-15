package com.amit.al.commands;

import com.amit.al.UserInteraction;
import com.amit.al.core.Game;
import com.amit.al.core.player.Player;
import com.amit.al.entities.Coordinates;
import com.amit.al.entities.FoodEntity;
import com.amit.al.entities.WeaponEntity;
import com.amit.al.enemy.Enemy;
import com.amit.al.map.GameMap;

public class NearbyCommand implements Command {
    @Override
    public CommandResult execute() {
        UserInteraction userInteraction = UserInteraction.getInstance();
        Coordinates playerLocation = Player.getInstance().getLocation();
        GameMap gameMap = Game.getInstance().getGameMap();
        WeaponEntity weaponEntity = gameMap.getWeaponLocation().get(playerLocation);
        FoodEntity foodEntity = gameMap.getFoodLocation().get(playerLocation);
        Enemy enemy = gameMap.getEnemyLocation().get(playerLocation);
        boolean somethingAround = false;
        if(weaponEntity !=null){
            userInteraction.showMessageToUserln("See there is the " + weaponEntity.getName());
            userInteraction.showMessageToUserln(" and " + weaponEntity.getDescription());
            somethingAround = true;
        }
        if(foodEntity !=null){
            userInteraction.showMessageToUserln("You have something to eat here a " + foodEntity.getName());
            userInteraction.showMessageToUserln(" and " + foodEntity.getDescription());
            somethingAround = true;
        }
        if(enemy !=null){
            userInteraction.showMessageToUserln("Right ahead we have " + enemy.getName());
            userInteraction.showMessageToUserln("Description: "+ enemy.getDescription());
            somethingAround = true;
        }
        if(!somethingAround){
            userInteraction.showMessageToUserln("Nothing around, keep moving");
            return CommandResult.NOT_EXECUTED;

        }

        return CommandResult.SUCCESS;
    }
}
