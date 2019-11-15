package com.amit.al;

import java.util.Scanner;

/**
 * singleton
 */
public class UserInteraction {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    private static UserInteraction userInteraction;

    private UserInteraction() {
    }

    public static UserInteraction getInstance() {
        if (userInteraction == null)
            userInteraction =  new UserInteraction();
        return userInteraction;
    }

    public void showMessageToUserln(String message) {
        showMessageToUserln(message, ANSI_BLACK);
    }

    public void showMessageToUserln(String message, String color) {
        System.out.println(color + message);
    }

    public void showMessageToUser(String message) {
        showMessageToUser(message, ANSI_BLACK);
    }
    public void showMessageToUser(String message, String color) {
        System.out.print(color + message);
    }

    public void newLine() {
        System.out.println();
    }

    public void separator(String colour) {
        System.out.println(colour + "--------------------------");
    }

    public String takeInputsFromUser() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public void loadedFailed(String message) {
        showMessageToUserln("Loading of " + message + " failed...");
    }

    public void loadedSuccessful(String message) {
        showMessageToUserln("Loading of " + message + " was successful...");
    }
}
