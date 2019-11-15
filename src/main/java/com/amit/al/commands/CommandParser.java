package com.amit.al.commands;

import com.amit.al.UserInteraction;

public class CommandParser {
    UserInteraction userInteraction = UserInteraction.getInstance();

    /**
     * Parses the command and executes it. If execution is successful returns true.
     *
     * @param userCommand
     */
    public CommandResult parse(String userCommand) {
        userCommand = userCommand.toLowerCase().trim();
        String[] commandString =userCommand.split(" ");
        if (isCommandValid(commandString[0])) {
            Command command = getAppropriateCommand(commandString[0], userCommand);
            if (command != null) {
                return command.execute();
            } else {
                userInteraction.showMessageToUserln("My brain froze and I couldn't complete that command. " +
                        "You have to try again.");
                return CommandResult.NOT_EXECUTED;
            }

        } else {
            userInteraction.showMessageToUserln("I don't get your command. Ask for help if you need.");
            return CommandResult.NOT_EXECUTED;
        }
    }


    private Command getAppropriateCommand(String commandOnly, String completeCommand) {
        CommandFactory commandFactory = new CommandFactory();
        return commandFactory.getCommand(commandOnly, completeCommand);
    }

    private boolean isCommandValid(String userCommand) {
        return CommandFactory.isValidCommand(userCommand.trim());

    }
}
