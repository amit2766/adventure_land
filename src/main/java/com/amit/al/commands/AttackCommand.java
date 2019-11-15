package com.amit.al.commands;

import com.amit.al.UserInteraction;
import com.amit.al.core.Game;
import com.amit.al.core.player.Player;
import com.amit.al.entities.Coordinates;
import com.amit.al.enemy.Enemy;
import com.amit.al.exceptions.DeathException;

import java.util.Map;

/**
 * This command can be used for attackPlayer for the enemy present in same location.
 */
public class AttackCommand implements Command {

    private UserInteraction userInteraction = UserInteraction.getInstance();

    @Override
    public CommandResult execute() {
        Player player = Player.getInstance();
        Game game = Game.getInstance();
        Map<Coordinates, Enemy> enemyLocation = game.getGameMap().getEnemyLocation();
        Enemy enemy = enemyLocation.get(player.getLocation());
        if (enemy == null) {
            userInteraction.showMessageToUserln("There is no one to attack, don't waste your energy");
            return CommandResult.NOT_EXECUTED;
        } else {
            try {
                Enemy attackedEnemy = attackEnemy(enemy, player);
                if (null == attackedEnemy) {
                    enemyLocation.remove(player.getLocation());
                    int oldXp = player.getProfile().getExperience();
                    player.getProfile().setExperience(oldXp + enemy.getExperience());
                    game.getGameMap().getLocationHint().replace(player.getLocation(),"none");
                    userInteraction.showMessageToUserln("Good you have killed the enemy you can move ahead. Stats changed.");
                } else {
                    userInteraction.showMessageToUserln("ouch..");
                    userInteraction.showMessageToUserln("You attacked him and he attacked you.. that's how it works.. unless one dies.. keep attacking");
                }
            } catch (DeathException e) {
                userInteraction.showMessageToUserln("You were killed by... " + e.getMessage());
                return CommandResult.EXIT_GAME;
            }
            return CommandResult.SUCCESS;

        }
    }

    /**
     * These calculations can be improved to include more variables.
     */
    private Enemy attackEnemy(Enemy enemy, Player player) throws DeathException {
        int health = enemy.getHealth();
        health = health - player.getProfile().getAttack();
        if (health > 0) {
            enemy.setHealth(health);
            enemy.attackPlayer(player);
            return enemy;
        }
        return null;
    }
}
