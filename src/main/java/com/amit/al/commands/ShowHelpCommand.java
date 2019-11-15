package com.amit.al.commands;

import com.amit.al.UserInteraction;

public class ShowHelpCommand implements Command {

    @Override
    public CommandResult execute() {
        UserInteraction userInteraction = UserInteraction.getInstance();
        userInteraction.showMessageToUserln(" -- HELP -- ", UserInteraction.ANSI_BLUE);

        userInteraction.showMessageToUserln("Below are sets of command that can be executed");

        CommandFactory.getCommands().forEach((k, v) -> {
            userInteraction.showMessageToUser(k, UserInteraction.ANSI_BLUE);
            userInteraction.showMessageToUserln(" - " + v);
        });
        return CommandResult.SUCCESS;
    }

}
