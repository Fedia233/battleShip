package com.battleship.enumeration;

public enum ShipType {

    SINGLE_DECKER(1), DOUBLE_DECKER(2), THREE_DECKER(3), FOUR_DECKER(4);

    int deckerCount;
    int health;

    ShipType(int deckerCount) {
        this.deckerCount = deckerCount;
    }

    public int getDeckerCount() {
        return deckerCount;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
