package com.amit.al.entities;

import com.amit.al.enemy.AbstractEnemy;

public class EnemyEntity {
    private String name;
    private String description;
    private String type;
    private int health;
    private int attack;
    private int defense;
    private int agility;
    private int experience;
    private int level;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getAgility() {
        return agility;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * Takes an abstract enemy and fill details from entity
     * @param abstractEnemy
     * @return
     */
    public AbstractEnemy toEnemy(AbstractEnemy abstractEnemy){
        abstractEnemy.setName(this.getName());
        abstractEnemy.setDescription(this.getDescription());
        abstractEnemy.setHealth(this.getHealth());
        abstractEnemy.setAttack(this.getAttack());
        abstractEnemy.setDefense(this.getDefense());
        abstractEnemy.setAgility(this.getAgility());
        abstractEnemy.setExperience(this.getExperience());
        abstractEnemy.setLevel(this.getLevel());
        return abstractEnemy;
    }
}
