package com.amit.al.commands;

import com.amit.al.UserInteraction;
import com.amit.al.core.Game;
import com.amit.al.core.player.Player;
import com.amit.al.entities.Coordinates;
import com.amit.al.map.Direction;


public class MovePlayerCommand implements Command {

    private final String completeCommand;
    UserInteraction userInteraction = UserInteraction.getInstance();

    public MovePlayerCommand(String completeCommand) {
        this.completeCommand = completeCommand;
    }

    @Override
    public CommandResult execute() {
        String[] s = completeCommand.split(" ");
        if (s.length == 2) {
            Direction direction;
            String directionString = s[1].toUpperCase();
            try {
                direction = Direction.valueOf(directionString);
            } catch (Exception e) {
                return CommandResult.FAILED;
            }
            return movePlayer(directionString, direction);
        } else
            return CommandResult.FAILED;
    }

    private CommandResult movePlayer(String movement, Direction direction) {
        Player player = Player.getInstance();
        Game game = Game.getInstance();
        Coordinates currentLocation = player.getLocation();
        String directionStrings = game.getGameMap().getDirectionString().get(currentLocation);
        if (directionStrings.contains(movement) && !isEnemyPresent(currentLocation)) {
            int x = currentLocation.getX() + direction.getX();
            int y = currentLocation.getY() + direction.getY();
            player.setLocation(new Coordinates(x, y));
            userInteraction.showMessageToUserln(" You have walked to  " + x + ", " + y);
            return CommandResult.SUCCESS;
        } else {
            userInteraction.showMessageToUserln("I cannot go to that direction try again");
            return CommandResult.NOT_EXECUTED;

        }


    }

    private boolean isEnemyPresent(Coordinates currentLocation) {
        Game game = Game.getInstance();
        if(game.getGameMap().getEnemyLocation().get(currentLocation) == null){
            return false;
        } else{
            userInteraction.showMessageToUserln("are you blind can't you see enemy standing?");
            return true;
        }
    }
}
