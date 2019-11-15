package com.amit.al.commands;

import com.amit.al.UserInteraction;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.assertEquals;

@RunWith(PowerMockRunner.class)
@PrepareForTest({UserInteraction.class, CommandFactory.class})

public class CommandParserTest {

    CommandParser parser = new CommandParser();

    @Before
    public void setUp() throws Exception {
        PowerMockito.mockStatic(UserInteraction.class);
        PowerMockito.when(UserInteraction.getInstance()).thenReturn(userInteraction);
    }

    @Mock
    UserInteraction userInteraction;

    @Test
    public void shouldNotExecuteTheCommandWhenCommandIsInvalid() {
        CommandResult result = parser.parse("invalid");
        assertEquals(CommandResult.NOT_EXECUTED, result);

    }

    @Test
    public void shouldExecuteCommandIfCommandIsAppropriate() throws Exception {
        CommandResult result = parser.parse("help");
        assertEquals(CommandResult.SUCCESS, result);

    }
}