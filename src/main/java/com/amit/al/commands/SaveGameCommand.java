package com.amit.al.commands;

import com.amit.al.UserInteraction;
import com.amit.al.core.Game;
import com.amit.al.core.player.Player;
import com.amit.al.repository.MapRepository;
import com.amit.al.repository.PlayerRepository;

import java.io.IOException;

import static com.amit.al.utilities.Constants.MAP_NAME;
import static com.amit.al.utilities.Constants.PROFILE_NAME;

public class SaveGameCommand implements Command {
    UserInteraction userInteraction = UserInteraction.getInstance();

    @Override
    public CommandResult execute(){
        userInteraction.showMessageToUserln("Saving game...");
        try {
            new PlayerRepository().savePlayer(PROFILE_NAME, Player.getInstance());
            new MapRepository().saveMap(MAP_NAME, Game.getInstance());
            userInteraction.showMessageToUserln("Game saved successfully");
            return CommandResult.SUCCESS;
        } catch (IOException e) {
            userInteraction.showMessageToUserln("Something went wrong while saving game");
            return CommandResult.FAILED;
        }
    }

}
