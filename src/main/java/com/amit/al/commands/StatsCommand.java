package com.amit.al.commands;

import com.amit.al.core.player.Player;
import com.amit.al.core.player.PlayerProfile;
import com.amit.al.UserInteraction;

public class StatsCommand implements Command {
    @Override
    public CommandResult execute() {
        Player player = Player.getInstance();
        PlayerProfile profile = player.getProfile();
        UserInteraction userInteraction = UserInteraction.getInstance();

        userInteraction.newLine();
        userInteraction.showMessageToUserln("Level: "+ player.getLevel());
        userInteraction.showMessageToUserln("Attack: "+ profile.getAttack());
        userInteraction.showMessageToUserln("Health: "+ profile.getHealth());
        userInteraction.showMessageToUserln("Experience: "+ profile.getExperience());
        userInteraction.showMessageToUserln("Defence: "+ profile.getDefence());
        userInteraction.showMessageToUserln("Agility: "+ profile.getAgility());
        userInteraction.newLine();
        return CommandResult.SUCCESS;
    }
}
