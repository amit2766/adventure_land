package com.amit.al.commands;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
public class CommandFactoryTest {

    CommandFactory factory = new CommandFactory();

    @Test
    public void shouldReturnAppropriateCommandInstanceWhenAskedFor() {
        Command help = factory.getCommand("help", "");
        Command save = factory.getCommand("save", "");
        Command map = factory.getCommand("map", "");
        Command move = factory.getCommand("move", "e");
        Command locate = factory.getCommand("locate", "");
        Command nearby = factory.getCommand("nearby", "");
        Command hint = factory.getCommand("hint", "");
        Command attack = factory.getCommand("attack", "");
        Command stats = factory.getCommand("stats", "");
        Command exit = factory.getCommand("exit", "");
        Command equip = factory.getCommand("equip", "");
        Command eat = factory.getCommand("eat", "");

        Assert.assertTrue(help instanceof ShowHelpCommand);
        Assert.assertTrue(save instanceof SaveGameCommand);
        Assert.assertTrue(map instanceof PrintMapCommand);
        Assert.assertTrue(move instanceof MovePlayerCommand);
        Assert.assertTrue(locate instanceof PrintLocationCommand);
        Assert.assertTrue(nearby instanceof NearbyCommand);
        Assert.assertTrue(hint instanceof HintCommand);
        Assert.assertTrue(attack instanceof AttackCommand);
        Assert.assertTrue(stats instanceof StatsCommand);
        Assert.assertTrue(exit instanceof ExitGameCommand);
        Assert.assertTrue(equip instanceof EquipItemCommand);
        Assert.assertTrue(eat instanceof EatFoodCommand);
    }

    @Test
    public void shouldGiveTrueWhenCommandIsValid() {
        boolean move = CommandFactory.isValidCommand("move");
        Assert.assertTrue(move);
    }

    @Test
    public void shouldFalseTrueWhenCommandIsValid() {
        boolean invalid = CommandFactory.isValidCommand("invalid");
        Assert.assertFalse(invalid);
    }
}