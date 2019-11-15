package com.amit.al.commands;

import java.util.HashMap;

public class CommandFactory {

    public Command getCommand(String commandName, String completeCommand){
        switch (commandName) {
            case "help":
                return new ShowHelpCommand();
            case "save":
                return new SaveGameCommand();
            case "map":
                return new PrintMapCommand();
            case "move":
                return new MovePlayerCommand(completeCommand);
            case "locate":
                return new PrintLocationCommand();
            case "nearby":
                return new NearbyCommand();
            case "hint":
                return new HintCommand();
            case "attack":
                return new AttackCommand();
            case "stats":
                return new StatsCommand();
            case "exit":
                return new ExitGameCommand();
            case "equip":
                return new EquipItemCommand();
            case "eat":
                return new EatFoodCommand();
            default:
                return null;
        }
    }

    private static HashMap<String, String> commands = new HashMap<>();

    static {
        commands.put("help", "Shows help to user");
        commands.put("map", "Prints  the map");
        commands.put("save", "Saves the game");
        commands.put("exit", "Exits the game without saving");
        commands.put("move", "Moves to given direction by one step.\n" +
                             "eg: 'move e' to move east. 'move w' to move west");
        commands.put("locate", "Shows your current location coordinates");
        commands.put("nearby", "Looks for things like enemy, weapon, food etc nearby");
        commands.put("attack", "Attacks an enemy");
        commands.put("stats", "Prints player stats");
        commands.put("eat", "Eats or drinks food or any consumable item");
        commands.put("equip", "Equip a weapon");
        commands.put("hint", "Prints hint for current location");

    }
    public static HashMap<String, String> getCommands(){
        return commands;
    }

    public static boolean isValidCommand(String commandKeyword){
        return commands.containsKey(commandKeyword);
    }
}
