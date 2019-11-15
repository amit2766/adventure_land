package com.amit.al.commands;

import com.amit.al.UserInteraction;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;

@RunWith(PowerMockRunner.class)
@PrepareForTest({UserInteraction.class})
public class ShowHelpCommandTest {

    ShowHelpCommand command = new ShowHelpCommand();

    @Mock
    UserInteraction userInteraction;


    @Before
    public void setUp() throws Exception {
        PowerMockito.mockStatic(UserInteraction.class);
        PowerMockito.when(UserInteraction.getInstance()).thenReturn(userInteraction);
    }

    @Test
    public void shouldDisplayHelpWhenAskedFor() {
        CommandResult result = command.execute();
        assertEquals(CommandResult.SUCCESS, result);

        Mockito.verify(userInteraction, Mockito.atLeastOnce()).showMessageToUserln(anyString(),anyString());
        Mockito.verify(userInteraction, Mockito.atLeastOnce()).showMessageToUserln(anyString());
    }
}