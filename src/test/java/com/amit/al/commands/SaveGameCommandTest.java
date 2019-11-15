package com.amit.al.commands;

import com.amit.al.UserInteraction;
import com.amit.al.core.Game;
import com.amit.al.core.player.Player;
import com.amit.al.repository.MapRepository;
import com.amit.al.repository.PlayerRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;

@RunWith(PowerMockRunner.class)
@PrepareForTest({Player.class,
        Game.class,
        UserInteraction.class,
        PlayerRepository.class,
        MapRepository.class,
        SaveGameCommand.class})
public class SaveGameCommandTest {

    SaveGameCommand command = new SaveGameCommand();
    @Mock
    Player player;

    @Mock
    Game game;

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

    }

    @Test
    public void shouldSaveGame() throws Exception {
        PlayerRepository pr = PowerMockito.mock(PlayerRepository.class);
        MapRepository mr = PowerMockito.mock(MapRepository.class);
        PowerMockito.whenNew(PlayerRepository.class).withAnyArguments().thenReturn(pr);
        PowerMockito.whenNew(MapRepository.class).withAnyArguments().thenReturn(mr);

        CommandResult result = command.execute();


        assertEquals(CommandResult.SUCCESS, result);

        Mockito.verify(pr).savePlayer(anyString(), eq(player));
        Mockito.verify(mr).saveMap(anyString(), eq(game));

    }

    @Test
    public void shouldNotSaveGameWhenExceptionThrown() throws Exception {
        PlayerRepository pr = PowerMockito.mock(PlayerRepository.class);
        MapRepository mr = PowerMockito.mock(MapRepository.class);
        PowerMockito.whenNew(PlayerRepository.class).withAnyArguments().thenReturn(pr);
        PowerMockito.whenNew(MapRepository.class).withAnyArguments().thenReturn(mr);
        Mockito.doThrow(new IOException()).when(mr).saveMap(anyString(), eq(game));

        CommandResult result = command.execute();


        assertEquals(CommandResult.FAILED, result);

        Mockito.verify(pr).savePlayer(anyString(), eq(player));



    }
}