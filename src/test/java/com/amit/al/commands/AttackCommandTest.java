package com.amit.al.commands;

import com.amit.al.core.Game;
import com.amit.al.core.player.Player;
import com.amit.al.core.player.PlayerProfile;
import com.amit.al.enemy.Enemy;
import com.amit.al.entities.Coordinates;
import com.amit.al.map.GameMap;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest({Game.class, Player.class})
public class AttackCommandTest {

    AttackCommand attackCommand = new AttackCommand();

    @Mock
    Player player;

    @Mock
    Game game;

    @Mock
    GameMap gameMap;

    @Before
    public void setUp() throws Exception {
        PowerMockito.mockStatic(Game.class);
        PowerMockito.mockStatic(Player.class);
        PowerMockito.when(Player.getInstance()).thenReturn(player);
        PowerMockito.when(Game.getInstance()).thenReturn(game);

        Mockito.when(game.getGameMap()).thenReturn(gameMap);

        PlayerProfile profile = new PlayerProfile("p",10,10,10,10,10);
        Mockito.when(player.getProfile()).thenReturn(profile);
    }

    @Test
    public void shouldAttackNoOneWhenNoEnemyNearBy() {
        Map<Coordinates, Enemy> map = new HashMap<>();
        Mockito.when(gameMap.getEnemyLocation()).thenReturn(map);

        CommandResult execute = attackCommand.execute();

        assertEquals(CommandResult.NOT_EXECUTED, execute);

    }

    @Test
    public void shouldAttackEnemyWhenEnemyIsPresentAndEnemyDiesInFirstAttack(){

        Map<Coordinates, Enemy> map = new HashMap<>();
        Enemy enemy = new Enemy();
        enemy.setHealth(1);
        Coordinates location = new Coordinates(1,1);
        map.put(location,  enemy);
        Mockito.when(player.getLocation()).thenReturn(location);
        Mockito.when(gameMap.getEnemyLocation()).thenReturn(map);

        CommandResult result = attackCommand.execute();
        assertEquals(CommandResult.SUCCESS, result);
        assertNull(map.get(location));

    }

    @Test
    public void shouldHaveIncreasedXPWhenEnemyGotKilled() {
        Map<Coordinates, Enemy> map = new HashMap<>();
        Enemy enemy = new Enemy();
        enemy.setHealth(1);
        enemy.setExperience(10);
        Coordinates location = new Coordinates(1,1);
        map.put(location,  enemy);
        Mockito.when(player.getLocation()).thenReturn(location);
        Mockito.when(gameMap.getEnemyLocation()).thenReturn(map);

        CommandResult result = attackCommand.execute();
        assertEquals(CommandResult.SUCCESS, result);
        assertEquals(20, player.getProfile().getExperience());
    }

    @Test
    public void shouldNotKillEnemyWhenEnemyHaveEnoughHealth(){
        Map<Coordinates, Enemy> map = new HashMap<>();
        Enemy enemy = new Enemy();
        enemy.setHealth(100);
        enemy.setExperience(10);
        Coordinates location = new Coordinates(1,1);
        map.put(location,  enemy);
        Mockito.when(player.getLocation()).thenReturn(location);
        Mockito.when(gameMap.getEnemyLocation()).thenReturn(map);

        CommandResult result = attackCommand.execute();
        assertEquals(CommandResult.SUCCESS, result);
        assertNotNull(map.get(location));

    }

    @Test
    public void shouldBeAttackedWhenEnemySurvivesFirstAttack() {
        Map<Coordinates, Enemy> map = new HashMap<>();
        Enemy enemy = new Enemy();
        enemy.setHealth(100);
        enemy.setExperience(10);
        enemy.setAttack(5);
        Coordinates location = new Coordinates(1,1);
        map.put(location,  enemy);
        Mockito.when(player.getLocation()).thenReturn(location);
        Mockito.when(gameMap.getEnemyLocation()).thenReturn(map);

        CommandResult result = attackCommand.execute();
        assertEquals(CommandResult.SUCCESS, result);
        assertEquals(5, player.getProfile().getHealth());
        assertNotNull(map.get(location));
    }


    @Test
    public void shouldExitWhenEnemysAttackKillsPlayer() {
        Map<Coordinates, Enemy> map = new HashMap<>();
        Enemy enemy = new Enemy();
        enemy.setHealth(100);
        enemy.setExperience(10);
        enemy.setAttack(20);
        Coordinates location = new Coordinates(1,1);
        map.put(location,  enemy);
        Mockito.when(player.getLocation()).thenReturn(location);
        Mockito.when(gameMap.getEnemyLocation()).thenReturn(map);

        CommandResult result = attackCommand.execute();
        assertEquals(CommandResult.EXIT_GAME, result);
    }
}