package com.amit.al.commands;

import com.amit.al.UserInteraction;
import com.amit.al.core.player.Player;
import com.amit.al.entities.Coordinates;
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
@PrepareForTest({UserInteraction.class, Player.class})
public class PrintLocationCommandTest {


    PrintLocationCommand command = new PrintLocationCommand();
    @Mock
    Player player;

    @Mock
    UserInteraction userInteraction;

    @Before
    public void setUp() throws Exception {
        PowerMockito.mockStatic(Player.class);
        PowerMockito.mockStatic(UserInteraction.class);

        PowerMockito.when(Player.getInstance()).thenReturn(player);
        PowerMockito.when(UserInteraction.getInstance()).thenReturn(userInteraction);
    }

    @Test
    public void shouldPrintCurrentLocationCoordinates() {

        Coordinates location = new Coordinates(1, 2);
        Mockito.when(player.getLocation()).thenReturn(location);

        CommandResult result = command.execute();

        Mockito.verify(userInteraction).showMessageToUserln("Your current location coordinates are: 1 , 2");
        assertEquals(CommandResult.SUCCESS, result);


    }
}