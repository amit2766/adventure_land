package com.amit.al.core;

import com.amit.al.core.player.Player;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest({Game.class, Player.class})
public class GameTest {

    @Mock
    private Player player;
    @Before
    public void setUp() throws Exception {
        PowerMockito.mockStatic(Game.class);
        PowerMockito.mockStatic(Player.class);
        PowerMockito.when(Player.createNewPlayer()).thenReturn(player);

    }

    @Test
    public void shouldCreateANewPlayerWhenNewGameStarts() {
    }
}