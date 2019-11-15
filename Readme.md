##**Adventure Land**

A command based single player game. 

##### Building the project:
1. Gradle is used to build the project.
2. It uses only two dependencies Gson and Log4j.
3. Unit tests are written using JUnit.
4. The game is tested on a Mac machine not in windows.

##### Starting the game:
1. Build the jar or using IDE execute. com.amit.al.AdventureLand is the main class.
2. First you will be asked to choose language.
3. After that you can start, load or exit the game.
4. If you start a new game any previous progress will be erased.
5. After starting a new game you have to create a new character.

#####  Playing the game:
1. You can type help to get in game help.
2. Game understands following commands:
   help - Shows help to user
   map - User the map
   save - Saves the game
   exit - Exits the game without saving
   move - Moves to given direction by one step.
            For example: 
            move e will take the player towards east by one step
            move w will take the player towards west by one step
            If the player cannot move to that direction due to some reason
            command will return appropriate response.
   locate - Shows your current location coordinates
   nearby - Looks for things like enemy, weapon, food etc nearby
   attack - Attacks an enemy
   stats - Prints player stats
   eat - Eats or drinks food or any consumable item
   equip - Equip a weapon
   hint - Prints hint for current location
3. If you think you cannot kill the enemy try going back and find foor or weapon.


##
#### Developers:
Game resources folder contains important information.

**game data folder:**

enemies.data.json: Contains list of enemies along with their skills.
food.data.json: All food items present in game.
weapons.data.json: Contains all information about weapon.

The above files gets loaded during the game load. 
maps folder contains 3 directories *.data.json contains data about map.
*map.txt is a visual representation of map presented to the user.
*.secret.txt is just for developers and can contain anything.


##### Further Development Plan
Update player location on map after move. 
Allow to equip and unequip items in a sack.
Move player to next level and allow further levels. This opens a wide possibility.
Save different player profiles.
Include different command arguments for greater flexibility.
Move all strings in properties file. 

Note: The game is in early alpha stage and may bug out or crash at any time.
  



Some of the game characters/location etc are inspired from my favourite childhood game Age of Mythology.
