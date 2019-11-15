package com.amit.al.commands;

public class ExitGameCommand implements Command {
    @Override
    public CommandResult execute() {
        return CommandResult.EXIT_GAME;
    }
}
