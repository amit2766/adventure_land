package com.amit.al.commands;

import com.amit.al.UserInteraction;
import com.amit.al.core.Game;
import com.amit.al.core.player.Player;
import com.amit.al.core.player.PlayerProfile;
import com.amit.al.enemy.Enemy;
import com.amit.al.entities.Coordinates;
import com.amit.al.entities.FoodEntity;
import com.amit.al.entities.WeaponEntity;
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
import static org.mockito.ArgumentMatchers.anyString;

@RunWith(PowerMockRunner.class)
@PrepareForTest({Game.class, Player.class, UserInteraction.class})
public class NearbyCommandTest {

    NearbyCommand command = new NearbyCommand();

    @Mock
    Player player;

    @Mock
    Game game;

    @Mock
    GameMap gameMap;

    @Mock
    UserInteraction userInteraction;

    @Before
    public void setUp() throws Exception {
        PowerMockito.mockStatic(Game.class);
        PowerMockito.mockStatic(Player.class);
        PowerMockito.mockStatic(UserInteraction.class);
        PowerMockito.when(Player.getInstance()).thenReturn(player);
        PowerMockito.when(Game.getInstance()).thenReturn(game);
        PowerMockito.when(UserInteraction.getInstance()).thenReturn(userInteraction);

        Mockito.when(game.getGameMap()).thenReturn(gameMap);

        PlayerProfile profile = new PlayerProfile("p", 10, 10, 10, 10, 10);
        Mockito.when(player.getProfile()).thenReturn(profile);


        Mockito.when(gameMap.getWeaponLocation()).thenReturn(new HashMap<>());
        Mockito.when(gameMap.getFoodLocation()).thenReturn(new HashMap<>());
        Mockito.when(gameMap.getEnemyLocation()).thenReturn(new HashMap<>());
    }

    @Test
    public void shouldShowNothingAroundWhenNothingIsPresentInGivenLocationBlock() {

        CommandResult result = command.execute();
        Mockito.verify(userInteraction).showMessageToUserln("Nothing around, keep moving");
        assertEquals(CommandResult.NOT_EXECUTED, result);

    }

    @Test
    public void shouldDisplayNearByItemsPresentOnLocation() {
        Coordinates location = new Coordinates(1, 1);

        mockWeaponMap(location);
        mockFoodLocation(location);
        mockEnemyLocation(location);

        CommandResult result = command.execute();
        assertEquals(CommandResult.SUCCESS, result);
        Mockito.verify(userInteraction, Mockito.times(6)).showMessageToUserln(anyString());



    }

    private void mockEnemyLocation(Coordinates location) {
        Map<Coordinates, Enemy> enemyMap = new HashMap<>();
        Enemy enemy = new Enemy();
        enemy.setHealth(1);
        enemyMap.put(location,  enemy);
        Mockito.when(player.getLocation()).thenReturn(location);
        Mockito.when(gameMap.getEnemyLocation()).thenReturn(enemyMap);
    }

    private void mockFoodLocation(Coordinates location) {
        Map<Coordinates, FoodEntity> foodMap = new HashMap<>();
        FoodEntity food = new FoodEntity();
        food.setHealth(5);
        foodMap.put(location, food);
        Mockito.when(player.getLocation()).thenReturn(location);
        Mockito.when(gameMap.getFoodLocation()).thenReturn(foodMap);
    }

    private void mockWeaponMap(Coordinates location) {
        Map<Coordinates, WeaponEntity> weaponMap = new HashMap<>();
        WeaponEntity weapon = new WeaponEntity();
        weapon.setAttack(5);
        weaponMap.put(location, weapon);
        Mockito.when(player.getLocation()).thenReturn(location);
        Mockito.when(gameMap.getWeaponLocation()).thenReturn(weaponMap);
    }
}