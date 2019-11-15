package com.amit.al.commands;

import com.amit.al.UserInteraction;
import com.amit.al.core.Game;
import com.amit.al.core.player.Player;
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
import static org.mockito.ArgumentMatchers.anyString;

@RunWith(PowerMockRunner.class)
@PrepareForTest({Game.class, Player.class, UserInteraction.class})
public class HintCommandTest {

    HintCommand command = new HintCommand();

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

    }

    @Test
    public void shouldShowHintForGivenLocationBlockWhenHintNotNone() {
        Map<Coordinates, String> map = new HashMap<>();
        Coordinates location = new Coordinates(1, 1);
        map.put(location, "test hint");
        Mockito.when(player.getLocation()).thenReturn(location);
        Mockito.when(gameMap.getLocationHint()).thenReturn(map);

        CommandResult result = command.execute();
        assertEquals(CommandResult.SUCCESS, result);
        Mockito.verify(userInteraction).showMessageToUserln("Hint: test hint");

    }

    @Test
    public void shouldShowNothingWhenNoHintPresent() {
        Map<Coordinates, String> map = new HashMap<>();
        Coordinates location = new Coordinates(1, 1);
        Mockito.when(player.getLocation()).thenReturn(location);
        Mockito.when(gameMap.getLocationHint()).thenReturn(map);

        CommandResult result = command.execute();
        assertEquals(CommandResult.NOT_EXECUTED, result);
        Mockito.verify(userInteraction, Mockito.times(0)).showMessageToUserln(anyString());
    }
}