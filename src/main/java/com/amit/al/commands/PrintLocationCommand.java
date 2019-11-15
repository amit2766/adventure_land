package com.amit.al.commands;

import com.amit.al.core.player.Player;
import com.amit.al.UserInteraction;

public class PrintLocationCommand implements Command {

    @Override
    public CommandResult execute() {
        Player player = Player.getInstance();
        UserInteraction.getInstance().
                showMessageToUserln("Your current location coordinates are: " +
                        player.getLocation().getX() + " , " + player.getLocation().getY());
        return CommandResult.SUCCESS;
    }
}
