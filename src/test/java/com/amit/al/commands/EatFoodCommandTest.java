package com.amit.al.commands;

import com.amit.al.core.Game;
import com.amit.al.core.player.Player;
import com.amit.al.core.player.PlayerProfile;
import com.amit.al.entities.Coordinates;
import com.amit.al.entities.FoodEntity;
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
import static org.junit.Assert.assertNull;

@RunWith(PowerMockRunner.class)
@PrepareForTest({Game.class, Player.class})
public class EatFoodCommandTest {

    EatFoodCommand command = new EatFoodCommand();
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

        PlayerProfile profile = new PlayerProfile("p", 10, 10, 10, 10, 10);
        Mockito.when(player.getProfile()).thenReturn(profile);
    }

    @Test
    public void shouldDoNothingWhenNoFoodNearby() {

        Map<Coordinates, FoodEntity> map = new HashMap<>();
        Mockito.when(gameMap.getFoodLocation()).thenReturn(map);

        CommandResult execute = command.execute();

        assertEquals(10, player.getProfile().getHealth());
        assertEquals(CommandResult.NOT_EXECUTED, execute);

    }

    @Test
    public void shouldGainHealthWhenFoodIsEaten() {
        Map<Coordinates, FoodEntity> map = new HashMap<>();
        FoodEntity food = new FoodEntity();
        food.setHealth(5);
        Coordinates location = new Coordinates(1, 1);
        map.put(location, food);
        Mockito.when(player.getLocation()).thenReturn(location);
        Mockito.when(gameMap.getFoodLocation()).thenReturn(map);

        CommandResult execute = command.execute();
        assertEquals(15, player.getProfile().getHealth());
        assertEquals(CommandResult.SUCCESS, execute);

    }

    @Test
    public void shouldRemoveHintAndFoodFromLocationAfterEating() {
        Coordinates location = new Coordinates(1, 1);

        Map<Coordinates, FoodEntity> foodMap = new HashMap<>();
        FoodEntity food = new FoodEntity();
        food.setHealth(5);
        foodMap.put(location, food);

        Map<Coordinates, String> hintMap = new HashMap<>();
        hintMap.put(location,"food hint");

        Mockito.when(player.getLocation()).thenReturn(location);
        Mockito.when(gameMap.getFoodLocation()).thenReturn(foodMap);
        Mockito.when(gameMap.getLocationHint()).thenReturn(hintMap);

        CommandResult execute = command.execute();
        assertEquals(15, player.getProfile().getHealth());
        assertNull(gameMap.getFoodLocation().get(location));
        assertEquals("none", gameMap.getLocationHint().get(location));
        assertEquals(CommandResult.SUCCESS, execute);
    }
}