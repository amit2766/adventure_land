package com.amit.al;

import com.amit.al.core.Game;
import com.amit.al.utilities.UserInteraction;

import java.io.File;

public class MainMenu extends Menu {

    UserInteraction userInteraction = UserInteraction.getInstance();

    @Override
    public void createMenu() {
        menuItems.add(new MenuItem("Start", "Starts a new game"));
        menuItems.add(new MenuItem("Load", "Load old game"));
        menuItems.add(new MenuItem("Exit", "Exit the application"));

    }

    @Override
    public void displayMenu() {
        userInteraction.newLine();
        int menuCount = 1;

        for (MenuItem menuItem :
                this.getMenuItems()) {
            userInteraction.showMessageToUser(menuCount + " - ");
            userInteraction.showMessageToUser(menuItem.item, UserInteraction.ANSI_BLUE);
            userInteraction.showMessageToUserln(" - " + menuItem.itemDescription);
            menuCount++;
        }

    }

    @Override
    public int selectMenuItem() {
        String selection = userInteraction.takeInputsFromUser();
        try {
            int itemChosen = Integer.parseInt(selection);
            if (itemChosen >= (this.getMenuItems().size() + 1) || itemChosen <= 0) {
                userInteraction.showMessageToUserln("Please choose number from above range only");
                return 0;
            }
            return itemChosen;
        } catch (NumberFormatException nfe) {
            userInteraction.showMessageToUserln("Invalid menu item selected please choose a number");
            return 0;
        }

    }

    @Override
    public void takeActionOnItemSelected(int itemNumber) {
        switch (itemNumber) {
            case 1:
                startNewGame();
                break;
            case 2:
                loadOldGame();
                break;
            case 3:
                exitGame();
                break;
            default:
                userInteraction.showMessageToUserln("Operation not allowed");
                break;
        }
    }

    private void startNewGame() {
        userInteraction.showMessageToUserln("Before we begin we need to create a player for you.");
        Game game = Game.getInstance();
        game.startNewGame();
        game.gameCommands();

    }

    private void loadOldGame() {
        if (!isOldProfilePresent()) {
            userInteraction.showMessageToUserln("No old game found please start a new game");
            redisplayMainMeny();
        } else {
            Game game = Game.getInstance();
            game.resumeOldGame();
            game.gameCommands();
        }

    }

    private void exitGame() {
        userInteraction.showMessageToUserln("Are you sure you want to exit? y/n");
        String exitConfirm = userInteraction.takeInputsFromUser();
        if ("y".equalsIgnoreCase(exitConfirm)) {
            userInteraction.showMessageToUserln("Exiting");
        } else if ("n".equalsIgnoreCase(exitConfirm)) {
            redisplayMainMeny();
        } else {
            userInteraction.showMessageToUserln("Please enter either y or n");
            exitGame();
        }

    }

    private void redisplayMainMeny() {
        displayMenu();
        int i = selectMenuItem();
        takeActionOnItemSelected(i);
    }

    private boolean isOldProfilePresent() {
        String[] dir = new File("saved_game").list();
        if (dir != null) {
            int totalProfiles = dir.length;
            return !(totalProfiles == 0);
        }
        return false;
    }



}
