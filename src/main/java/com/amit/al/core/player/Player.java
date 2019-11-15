package com.amit.al.core.player;

import com.amit.al.UserInteraction;
import com.amit.al.commands.CommandFactory;
import com.amit.al.core.Game;
import com.amit.al.entities.Coordinates;
import com.amit.al.entities.PlayerEntity;
import com.amit.al.entities.WeaponEntity;
import com.amit.al.map.LocationType;
import com.amit.al.repository.PlayerRepository;

import java.util.ArrayList;

/**
 * Singleton. Only one player in a game. Right now only singleton ;-)
 */
public class Player {

    private static Player player;
    private Coordinates location;
    private PlayerProfile profile;
    /**
     * Our player for now can have only one weapon.
     */
    private WeaponEntity weapon;
    private int level;

    private Player() {

    }

    /**
     * Will return null if player is not loaded or created earlier.
     * @return singleton player instance.
     */
    public static Player getInstance() {
        return player;
    }


    /**
     * @return player instance with filled details
     */
    public static Player createNewPlayer() {
        if (player == null) {
            player = new Player();
            PlayerProfile playerProfile = new PlayerProfile();
            player.setProfile(playerProfile);
            player.setLocation(new Coordinates(0, 0));
            player.level = 1;
            return player;
        }
        return player;
    }

    public static Player loadOldPlayer() {
        if (player == null) {
            player = new Player();
            PlayerEntity entity = readPlayerFromFile("saved_game/player.data.json");
            if (entity != null) {
                player.setLocation(new Coordinates(entity.getLocation()));
                PlayerProfile profile = new PlayerProfile(entity.getName(), entity.getHealth(),
                        entity.getAttack(), entity.getAgility(), entity.getDefence(), entity.getExperience());
                player.setProfile(profile);
                player.setLevel(entity.getLevel());
                String weaponName = entity.getWeapon();
                if(!"none".equalsIgnoreCase(weaponName) ){
                    ArrayList<WeaponEntity> weapons = Game.getInstance().getWeaponsListFromData();
                    for (WeaponEntity weapon :
                            weapons) {
                        if (weaponName.equalsIgnoreCase(weapon.getName())){
                            player.setWeapon(weapon);
                            break;
                        }
                    }
                }
            }
        }
        return player;
    }

    private static PlayerEntity readPlayerFromFile(String fileName) {
        PlayerRepository playerRepository = new PlayerRepository();
        return playerRepository.readPlayerProfile(fileName);
    }

    public Coordinates getLocation() {
        return location;
    }

    public void setLocation(Coordinates location) {
        if (this.getLocation() == null)
            this.location = location;
        if (!this.getLocation().equals(location)) {
            this.location = location;
            new CommandFactory().getCommand("hint", "hint").execute();
            if(Game.getInstance().getGameMap().getLocationType().get(location).equals(LocationType.DESTINATION)){
                player.setLevel(player.getLevel()+1);
                UserInteraction.getInstance().showMessageToUser("Level up loading new map ... ", UserInteraction.ANSI_PURPLE);
                // TODO: Load new map
            }
        }
    }

    public PlayerProfile getProfile() {
        return profile;
    }

    private void setProfile(PlayerProfile profile) {
        this.profile = profile;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public WeaponEntity getWeapon() {
        return weapon;
    }

    public void setWeapon(WeaponEntity weapon) {
        this.weapon = weapon;
    }
}
