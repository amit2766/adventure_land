package com.amit.al;

import com.amit.al.core.Game;
import com.amit.al.utilities.UserInteraction;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(PowerMockRunner.class)
@PrepareForTest({UserInteraction.class, Game.class, MainMenu.class})
public class MainMenuTest {

    @Mock
    private UserInteraction userInteraction;

    @Mock
    private Game game;

    @Before
    public void setUp() {
        PowerMockito.mockStatic(UserInteraction.class);
        PowerMockito.mockStatic(Game.class);
        PowerMockito.when(UserInteraction.getInstance()).thenReturn(userInteraction);
        PowerMockito.when(Game.getInstance()).thenReturn(game);
    }

    @Test
    public void shouldCreateMainMenuWithThreeMenuItems() {
        MainMenu mainMenu = new MainMenu();
        mainMenu.createMenu();
        assertEquals(3, mainMenu.menuItems.size());
    }

    @Test
    public void shouldDisplayMenuWithAllItems() {
        MainMenu mainMenu = Mockito.spy(new MainMenu());
        Mockito.when(mainMenu.getMenuItems()).thenReturn(dummyMenu());

        mainMenu.displayMenu();

        Mockito.verify(userInteraction,Mockito.times(3)).showMessageToUser(Mockito.anyString());
        Mockito.verify(userInteraction,Mockito.times(3)).showMessageToUser(Mockito.anyString(),Mockito.anyString());
        Mockito.verify(userInteraction,Mockito.times(3)).showMessageToUserln(Mockito.anyString());
    }

    @Test
    public void shouldReturnSelectedItemNumber(){
        MainMenu mainMenu = Mockito.spy(new MainMenu());
        Mockito.when(mainMenu.getMenuItems()).thenReturn(dummyMenu());

        Mockito.when(userInteraction.takeInputsFromUser()).thenReturn("1");

        int i = mainMenu.selectMenuItem();
        assertEquals(1,i);

    }

    @Test
    public void shouldReturnZeroWhenInvalidNumberSelected(){

        MainMenu mainMenu = Mockito.spy(new MainMenu());
        Mockito.when(mainMenu.getMenuItems()).thenReturn(dummyMenu());
        Mockito.when(userInteraction.takeInputsFromUser()).thenReturn("5");

        int input1 = mainMenu.selectMenuItem();
        assertEquals(0,input1);

        Mockito.when(userInteraction.takeInputsFromUser()).thenReturn("0");

        int input2 = mainMenu.selectMenuItem();
        assertEquals(0,input2);

        Mockito.when(userInteraction.takeInputsFromUser()).thenReturn("-3");

        int input3 = mainMenu.selectMenuItem();
        assertEquals(0,input3);

        Mockito.when(userInteraction.takeInputsFromUser()).thenReturn("ABC");

        int input4 = mainMenu.selectMenuItem();
        assertEquals(0,input4);

    }

    @Test
    public void shouldDisplayMessageWhenInvalidItemNumberPresent() {
        MainMenu mainMenu = Mockito.spy(new MainMenu());
        mainMenu.takeActionOnItemSelected(4);
        Mockito.verify(userInteraction).showMessageToUserln("Operation not allowed");
    }

    @Test
    public void shouldStartNewGameWhen1Chosen() {
        MainMenu mainMenu = Mockito.spy(new MainMenu());

        mainMenu.takeActionOnItemSelected(1);

        Mockito.verify(game).startNewGame();
        Mockito.verify(game).gameCommands();
    }

    @Test
    public void shouldLoadOldGameWhen2ChosenAndOldGameExists() throws Exception {
        MainMenu mainMenu = PowerMockito.spy(new MainMenu());
        PowerMockito.doReturn(true).when(mainMenu, "isOldProfilePresent");

        mainMenu.takeActionOnItemSelected(2);

        Mockito.verify(game).resumeOldGame();;
        Mockito.verify(game).gameCommands();;
    }

    @Test
    public void shouldDisplayMenuItemsWhenNoProfileExistsAnd2Chosen() throws Exception {
        MainMenu mainMenu = PowerMockito.spy(new MainMenu());
        PowerMockito.doReturn(false).when(mainMenu, "isOldProfilePresent");

        mainMenu.takeActionOnItemSelected(2);

        Mockito.verify(mainMenu, Mockito.times(1)).displayMenu();
        Mockito.verify(mainMenu, Mockito.times(1)).selectMenuItem();
        Mockito.verify(mainMenu, Mockito.times(2)).takeActionOnItemSelected(Mockito.anyInt());
    }

    @Test
    public void shouldExitGameWhen3AndYChosen() {

        Mockito.when(userInteraction.takeInputsFromUser()).thenReturn("y");
        MainMenu mainMenu = Mockito.spy(new MainMenu());

        mainMenu.takeActionOnItemSelected(3);

        Mockito.verify(userInteraction).showMessageToUserln("Exiting");

    }

    @Test
    public void shouldAskAgainAboutExitingGameWhen3AndRandomAlphabetChosen() {

        Mockito.when(userInteraction.takeInputsFromUser()).thenReturn("qwe").thenReturn("y");
        MainMenu mainMenu = Mockito.spy(new MainMenu());

        mainMenu.takeActionOnItemSelected(3);

        Mockito.verify(userInteraction).showMessageToUserln("Please enter either y or n");
        Mockito.verify(userInteraction,Mockito.times(2)).showMessageToUserln("Are you sure you want to exit? y/n");


    }

    @Test
    public void shouldDisplayMenuAgainWhen3AndNChosen() {
        Mockito.when(userInteraction.takeInputsFromUser()).thenReturn("n");
        MainMenu mainMenu = Mockito.spy(new MainMenu());

        mainMenu.takeActionOnItemSelected(3);

        Mockito.verify(mainMenu, Mockito.times(1)).displayMenu();
        Mockito.verify(mainMenu, Mockito.times(1)).selectMenuItem();
        Mockito.verify(mainMenu, Mockito.times(2)).takeActionOnItemSelected(Mockito.anyInt());

    }

    private List<MenuItem> dummyMenu() {
        List<MenuItem> list = new ArrayList<>();
        list.add(new MenuItem("start","start"));
        list.add(new MenuItem("load","load"));
        list.add(new MenuItem("exit","exit"));
        return list;
    }

}