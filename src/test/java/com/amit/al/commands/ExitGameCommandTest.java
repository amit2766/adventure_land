package com.amit.al.commands;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.assertEquals;

@RunWith(PowerMockRunner.class)
public class ExitGameCommandTest {
    @Test
    public void shouldExitWhenCalled() {
        ExitGameCommand command = new ExitGameCommand();
        CommandResult result = command.execute();
        assertEquals(CommandResult.EXIT_GAME, result);

    }
}