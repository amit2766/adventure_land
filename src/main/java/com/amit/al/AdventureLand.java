package com.amit.al;

import com.amit.al.utilities.UserInteraction;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * This is the entry point for the game. This creates and entry menu and that will decide course of game.
 * Log4j is used for logging
 */
public class AdventureLand {
    private static Log logger = LogFactory.getLog(com.amit.al.AdventureLand.class);

    public static void main(String[] args) {
        logger.info("Adventure Started ");

        UserInteraction userInteraction = UserInteraction.getInstance();
        userInteraction.separator(UserInteraction.ANSI_BLACK);

        LanguageSelector languageSelector = LanguageSelector.getInstance();
        languageSelector.displayLanguageSelection();

        showMainMenu();

    }

    private static void showMainMenu() {
        MainMenu mainMenu = new MainMenu();
        mainMenu.createMenu();
        int selectedItem = 0;
        while (selectedItem == 0){
            mainMenu.displayMenu();
            selectedItem = mainMenu.selectMenuItem();
        }
        mainMenu.takeActionOnItemSelected(selectedItem);
    }

}
