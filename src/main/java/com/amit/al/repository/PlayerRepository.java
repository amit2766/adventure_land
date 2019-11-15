package com.amit.al.repository;

import com.amit.al.core.player.Player;
import com.amit.al.core.player.PlayerProfile;
import com.amit.al.entities.Coordinates;
import com.amit.al.entities.PlayerEntity;
import com.amit.al.entities.WeaponEntity;
import com.amit.al.utilities.CustomFileReader;
import com.google.gson.Gson;

import java.io.*;

/**
 * This is persistence layer. Here Objects will be saved to json and will be read from json for Player.
 */
public class PlayerRepository {

    public void savePlayer(String profileName, Player player) throws IOException {
        WeaponEntity weapon = player.getWeapon();
        Coordinates playerLocation = player.getLocation();
        int playerLevel = player.getLevel();
        PlayerProfile playerProfile = player.getProfile();

        PlayerEntity playerEntity = new PlayerEntity();
        playerEntity.setName(playerProfile.getName());
        playerEntity.setHealth(playerProfile.getHealth());
        playerEntity.setAttack(playerProfile.getAttack());
        playerEntity.setAgility(playerProfile.getAgility());
        playerEntity.setDefence(playerProfile.getDefence());
        playerEntity.setExperience(playerProfile.getExperience());
        playerEntity.setLocation(playerLocation.toString());
        playerEntity.setLevel(playerLevel);


        if (weapon != null) {
            playerEntity.setWeapon(weapon.getName());
        } else{
            playerEntity.setWeapon("none");
        }
        new File(profileName).getParentFile().mkdirs();
        Gson gson = new Gson();
        Writer writer = new FileWriter(profileName);
        gson.toJson(playerEntity, writer);
        writer.flush();
        writer.close();
    }

    public PlayerEntity readPlayerProfile(String fileName) {
        try {
            BufferedReader br = CustomFileReader.readFileFromLocation(fileName);
            return new Gson().fromJson(br, PlayerEntity.class);
        } catch (IOException e) {
            return null;
        }
    }
}
