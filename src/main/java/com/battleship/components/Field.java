package com.battleship.components;

import java.util.ArrayList;
import java.util.List;

public class Field {

    private int fieldSize;
    private List<Ship> ships = new ArrayList<>();

    public Field(int fieldSize) {
        this.fieldSize = fieldSize;
    }

    public void addShip(Ship newShip) {
        ships.add(newShip);
    }

    public void removeShip(Ship ship) {
        ships.remove(ship);
    }

    public int getFieldSize() {
        return fieldSize;
    }

    public boolean canBePlaced(Ship ship) {
        Rectangle newShipPrivateRectangle = ship.outlineTheShip();
        return ships.stream()
                .map(Ship::outlineTheShipAndItTerritory)
                .filter(existingShipRectangle -> existingShipRectangle.intersectsWith(newShipPrivateRectangle))
                .count() <= 0;
    }

    public List<Ship> getShips() {
        return ships;
    }
}
