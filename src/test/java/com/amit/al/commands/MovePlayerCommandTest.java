package com.amit.al.commands;

import com.amit.al.UserInteraction;
import com.amit.al.core.Game;
import com.amit.al.core.player.Player;
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

import static org.junit.Assert.assertEquals;

@RunWith(PowerMockRunner.class)
@PrepareForTest({Game.class, Player.class, UserInteraction.class})
public class MovePlayerCommandTest {



    @Mock
    Player player;

    @Mock
    Game game;

    @Mock
    UserInteraction userInteraction;

    @Mock
    GameMap gameMap;

    @Before
    public void setUp() throws Exception {
        PowerMockito.mockStatic(Game.class);
        PowerMockito.mockStatic(Player.class);
        PowerMockito.mockStatic(UserInteraction.class);
        PowerMockito.when(Player.getInstance()).thenReturn(player);
        PowerMockito.when(Game.getInstance()).thenReturn(game);
        PowerMockito.when(UserInteraction.getInstance()).thenReturn(userInteraction);
        Mockito.when(game.getGameMap()).thenReturn(gameMap);
        Mockito.when(player.getLocation()).thenReturn(new Coordinates(0,0));

    }

    @Test
    public void shouldFailCommandWhenDirectionIsNotValid() {
        MovePlayerCommand command = new MovePlayerCommand("move nowhere");
        CommandResult result = command.execute();
        assertEquals(CommandResult.FAILED, result);


    }

    @Test
    public void shouldNotMoveToDirectionWhenNotAllowed() {
        Map<Coordinates, String> map = new HashMap<>();
        map.put(new Coordinates(0,0),"E");
        Mockito.when(gameMap.getDirectionString()).thenReturn(map);

        MovePlayerCommand command = new MovePlayerCommand("move w");

        CommandResult result = command.execute();

        assertEquals(CommandResult.NOT_EXECUTED, result);
        assertEquals(new Coordinates(0,0), player.getLocation());

    }

    @Test
    public void shouldNotMoveToDirectionWhenEnemyPresent() {
        Map<Coordinates, String> map = new HashMap<>();
        map.put(new Coordinates(0,0),"E");
        Mockito.when(gameMap.getDirectionString()).thenReturn(map);

        Map<Coordinates, Enemy> enemyMap = new HashMap<>();
        Enemy enemy = new Enemy();
        Coordinates location = new Coordinates(0,0);
        enemyMap.put(location,  enemy);
        Mockito.when(gameMap.getEnemyLocation()).thenReturn(enemyMap);

        MovePlayerCommand command = new MovePlayerCommand("move e");

        CommandResult result = command.execute();

        assertEquals(CommandResult.NOT_EXECUTED, result);
        assertEquals(new Coordinates(0,0), player.getLocation());

    }

    @Test
    public void shouldMovePlayerEastWhenUserCommands() {
        Map<Coordinates, String> map = new HashMap<>();
        map.put(new Coordinates(0,0),"E");
        Mockito.when(gameMap.getDirectionString()).thenReturn(map);

        MovePlayerCommand command = new MovePlayerCommand("move e");
        CommandResult result = command.execute();

        assertEquals(CommandResult.SUCCESS, result);
    }
}