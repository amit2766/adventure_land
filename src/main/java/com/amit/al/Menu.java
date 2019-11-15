package com.amit.al;

import java.util.ArrayList;
import java.util.List;

public abstract class Menu {
    protected List<MenuItem> menuItems = new ArrayList<>();

    public abstract void createMenu();
    public abstract void displayMenu();
    public abstract int selectMenuItem();
    public abstract void takeActionOnItemSelected(int itemNumber);

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

}
