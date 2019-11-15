package com.amit.al.commands;

import com.amit.al.UserInteraction;
import com.amit.al.core.Game;
import com.amit.al.core.player.Player;
import com.amit.al.entities.Coordinates;
import com.amit.al.entities.WeaponEntity;
import com.amit.al.map.GameMap;

import java.util.Map;

public class EquipItemCommand implements Command {
    @Override
    public CommandResult execute() {
        GameMap gameMap = Game.getInstance().getGameMap();
        Map<Coordinates, WeaponEntity> weapons = gameMap.getWeaponLocation();
        Player player = Player.getInstance();
        Coordinates location = player.getLocation();
        WeaponEntity weapon = weapons.get(location);
        if (weapon != null) {
            player.setWeapon(weapon);
            int oldAttack = player.getProfile().getAttack();
            int newAttack = oldAttack + weapon.getAttack();
            player.getProfile().setAttack(newAttack);
            UserInteraction.getInstance().showMessageToUserln("Attacked improved from " + oldAttack + " to " + newAttack);
            weapons.remove(location);
            gameMap.getLocationHint().replace(location, "none");
            return CommandResult.SUCCESS;
        } else {
            UserInteraction.getInstance().showMessageToUserln("There is no weapon here");
            return CommandResult.NOT_EXECUTED;
        }
    }
}
