package com.amit.al.core.player;

import com.amit.al.UserInteraction;

public class PlayerProfile extends AbstractProfile {
    UserInteraction userInteraction = UserInteraction.getInstance();

    public PlayerProfile(){
        fillNewPlayerDetails();
    }

    public PlayerProfile(String name,int health, int attack, int agility, int defence, int experience){
        fillOldPlayerDetails(name,health, attack, agility, defence, experience);

    }
    private void fillNewPlayerDetails() {
        String name = this.getPlayerName();
        fillOldPlayerDetails(name,10,10,10,10,10);
    }

    private void fillOldPlayerDetails(String name,int health, int attack, int agility, int defence, int experience){
        this.setName(name);
        this.setHealth(health);
        this.setAttack(attack);
        this.setAgility(agility);
        this.setDefence(defence);
        this.setExperience(experience);
    }



    private String getPlayerName() {
        userInteraction.showMessageToUserln("Hello, What is your name?");
        String playerName = userInteraction.takeInputsFromUser();
        if(!isNameValid(playerName)){
            userInteraction.showMessageToUser("only alphabets allowed and upto 6 characters");
            getPlayerName();
        }
        userInteraction.showMessageToUserln("Welcome to the game " + playerName);
        return playerName;
    }

    private boolean isNameValid(String playerName) {
        return playerName.matches("[a-z,A-Z]{1,6}");
    }


}
