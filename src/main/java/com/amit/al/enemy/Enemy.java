package com.amit.al.enemy;

import com.amit.al.core.player.Player;
import com.amit.al.exceptions.DeathException;

/**
 * Each enemy should extend abstract enemy and should have some basic properties.
 */
public class Enemy extends AbstractEnemy {


    @Override
    public void attackPlayer(Player player) throws DeathException {
        int health = player.getProfile().getHealth();
        health = health - this.getAttack();
        if(health <= 0 ){
            throw new DeathException(this.getName());
        }else {
            player.getProfile().setHealth(health);
        }
    }

}
