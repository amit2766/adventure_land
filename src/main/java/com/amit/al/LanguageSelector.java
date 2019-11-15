package com.amit.al;

import com.amit.al.utilities.UserInteraction;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Allows you to select language for your game. As game is text based having right language is important.
 * Singleton class.
 */
public class LanguageSelector {

    private UserInteraction userInteraction = UserInteraction.getInstance();
    private List<String> availableLanguages;
    private ResourceBundle resourceBundle;
    private static LanguageSelector languageSelector;

    private LanguageSelector() {
        availableLanguages = new ArrayList<>();
        availableLanguages.add("en");
        availableLanguages.add("de");
    }

    public static LanguageSelector getInstance(){
        if(languageSelector == null){
            languageSelector = new LanguageSelector();
        }
        return languageSelector;
    }



    public void displayLanguageSelection() {
        userInteraction.showMessageToUser("Your default language is set to English. Do you want to change it? ");
        userInteraction.showMessageToUserln("y/n" , UserInteraction.ANSI_GREEN);
        selectLanguage();

    }

    private void selectLanguage() {
        String input = userInteraction.takeInputsFromUser();

        if ("y".equalsIgnoreCase(input)) {
            showLanguageOptions();
        } else if ("n".equalsIgnoreCase(input)) {
            setLanguageAs("en");
        } else {
            userInteraction.showMessageToUserln("Please choose either y or n");
            selectLanguage();
        }
    }

    private void setLanguageAs(String language) {
        Locale currentLocale = new Locale(language);
        this.resourceBundle = ResourceBundle.getBundle("instructions", currentLocale);
        userInteraction.showMessageToUserln(resourceBundle.getString("languageset"));

    }

    public ResourceBundle getSelectedLanguageFile() {
        return this.resourceBundle;
    }

    private void showLanguageOptions() {
        userInteraction.showMessageToUserln("Please choose one from below. Case sensitive.");

        for (String language :
                availableLanguages) {
            userInteraction.showMessageToUserln(language);
        }

        String languageChosen = userInteraction.takeInputsFromUser();
        for (String language :
                availableLanguages) {
            if (language.equals(languageChosen)) {
                setLanguageAs(languageChosen);
                return;

            }
        }
        userInteraction.showMessageToUserln("Sorry, the language is not valid.");
        showLanguageOptions();
    }
}
