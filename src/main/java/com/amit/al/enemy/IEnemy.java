package com.amit.al.enemy;

import com.amit.al.core.player.Player;
import com.amit.al.exceptions.DeathException;

public interface IEnemy {
    void attackPlayer(Player player) throws DeathException;
}
