package com.amit.al.commands;

/**
 * command pattern
 */
@FunctionalInterface
public interface Command {
    CommandResult execute();
}
