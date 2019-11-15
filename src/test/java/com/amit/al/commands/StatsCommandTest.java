package com.amit.al.commands;

import com.amit.al.core.player.Player;
import com.amit.al.core.player.PlayerProfile;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.assertEquals;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Player.class)
public class StatsCommandTest {

    @Mock
    Player player;

    StatsCommand command = new StatsCommand();

    @Before
    public void setUp() throws Exception {
        PowerMockito.mockStatic(Player.class);
        PowerMockito.when(Player.getInstance()).thenReturn(player);


        PlayerProfile profile = new PlayerProfile("p", 10, 10, 10, 10, 10);
        Mockito.when(player.getProfile()).thenReturn(profile);
    }

    @Test
    public void shouldPrintPlayerStats() {
        CommandResult result = command.execute();
        assertEquals(CommandResult.SUCCESS, result);

    }
}