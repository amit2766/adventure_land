package com.amit.al.commands;

import com.amit.al.core.player.Player;
import com.amit.al.UserInteraction;
import com.amit.al.utilities.CustomFileReader;

import java.io.BufferedReader;
import java.io.IOException;

public class PrintMapCommand implements Command {

    UserInteraction userInteraction = UserInteraction.getInstance();

    @Override
    public CommandResult execute() {
        Player player = Player.getInstance();
        int playerLevel = player.getLevel();
        return showMap(playerLevel);

    }

    private CommandResult showMap(int playerLevel) {
        String fileName;
        switch (playerLevel) {
            case 1:
                fileName = "level1map.txt";
                break;
            case 2:
                fileName = "level2map.txt";
                break;
            case 3:
                fileName = "level3map.txt";
                break;
            default:
                fileName = null;

        }

        if (fileName != null) {
            String filePath = "gamedata/maps/" + fileName;
            try{
                BufferedReader br = CustomFileReader.readFileFromResourcesAsBR(filePath);
                String line;
                while ((line  = br.readLine()) != null) {
                    userInteraction.showMessageToUserln(line);
                }
            } catch (IOException e) {
                userInteraction.showMessageToUserln("I got blinded. can't read the map");
                return CommandResult.FAILED;
            }
            return CommandResult.SUCCESS;
        }
        userInteraction.showMessageToUserln("I can't find the map anywhere, something is not right");
        return CommandResult.FAILED;
    }

}
