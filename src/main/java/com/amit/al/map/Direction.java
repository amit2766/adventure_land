package com.amit.al.map;

public enum Direction {

    N("Towards North", 0, 1),
    S("Towards South", 0, -1),
    E("Towards East", 1, 0),
    W("Towards West", -1, 0);

    private final String description;
    private final int x;
    private final int y;

    Direction(String description, int x, int y) {
        this.description = description;
        this.x = x;
        this.y = y;
    }

    public String getDescription() {
        return description;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
