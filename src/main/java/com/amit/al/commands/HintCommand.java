package com.amit.al.commands;

import com.amit.al.UserInteraction;
import com.amit.al.core.Game;
import com.amit.al.core.player.Player;

public class HintCommand implements Command {
    @Override
    public CommandResult execute() {
        String hint = Game.getInstance().getGameMap().getLocationHint().get(Player.getInstance().getLocation());
        if (hint != null && !"none".equalsIgnoreCase(hint)) {
            UserInteraction.getInstance().showMessageToUserln("Hint: " + hint);
            return CommandResult.SUCCESS;
        }
        return CommandResult.NOT_EXECUTED;
    }
}
