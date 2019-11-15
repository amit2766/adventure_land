package com.amit.al.commands;

import com.amit.al.core.Game;
import com.amit.al.core.player.Player;
import com.amit.al.core.player.PlayerProfile;
import com.amit.al.entities.Coordinates;
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
import static org.junit.Assert.assertNull;

@RunWith(PowerMockRunner.class)
@PrepareForTest({Game.class, Player.class})
public class EquipItemCommandTest {

    EquipItemCommand command = new EquipItemCommand();

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
    public void shouldNotEquipItemWhenNothingPresent() {
        Map<Coordinates, WeaponEntity> map = new HashMap<>();
        Mockito.when(gameMap.getWeaponLocation()).thenReturn(map);

        CommandResult execute = command.execute();

        assertEquals(10, player.getProfile().getAttack());
        assertEquals(CommandResult.NOT_EXECUTED, execute);
    }

    @Test
    public void shouldIncreaseAttackWhenWeaponIsEquipped() {
        Map<Coordinates, WeaponEntity> map = new HashMap<>();
        WeaponEntity weapon = new WeaponEntity();
        weapon.setAttack(5);
        Coordinates location = new Coordinates(1, 1);
        map.put(location, weapon);
        Mockito.when(player.getLocation()).thenReturn(location);
        Mockito.when(gameMap.getWeaponLocation()).thenReturn(map);

        CommandResult execute = command.execute();
        assertEquals(15, player.getProfile().getAttack());
        assertEquals(CommandResult.SUCCESS, execute);
    }

    @Test
    public void shouldRemoveHintAndFoodFromLocationAfterEating() {
        Coordinates location = new Coordinates(1, 1);

        Map<Coordinates, WeaponEntity> weapons = new HashMap<>();
        WeaponEntity weapon = new WeaponEntity();
        weapon.setAttack(5);
        weapons.put(location, weapon);

        Map<Coordinates, String> hintMap = new HashMap<>();
        hintMap.put(location, "food hint");

        Mockito.when(player.getLocation()).thenReturn(location);
        Mockito.when(gameMap.getWeaponLocation()).thenReturn(weapons);
        Mockito.when(gameMap.getLocationHint()).thenReturn(hintMap);

        CommandResult execute = command.execute();

        assertEquals(15, player.getProfile().getAttack());
        assertNull(gameMap.getWeaponLocation().get(location));
        assertEquals("none", gameMap.getLocationHint().get(location));
        assertEquals(CommandResult.SUCCESS, execute);
    }
}