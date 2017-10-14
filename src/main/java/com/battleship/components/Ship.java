package com.battleship.components;

import com.battleship.enumeration.Direction;
import com.battleship.enumeration.ShipType;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Ship {

    private String name;
    private String state;
    private Point position;
    private Direction direction;
    private ShipType shipType;
    private HashMap<Integer, Integer> hitList = new HashMap<>();

    public Ship() {
    }

    public Ship(int positionX, int positionY, ShipType shipType, Direction direction) {
        this.name = "Ship " + positionX + " " + positionY + " ";
        this.position = new Point(positionX, positionY);
        this.shipType = shipType;
        this.direction = direction;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public Ship(int positionX, int positionY, ShipType shipType) {
        this.position = new Point(positionX, positionY);
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public ShipType getShipType() {
        return shipType;
    }

    public void setShipType(ShipType shipType) {
        this.shipType = shipType;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public String getState() { return state; }

    public void canHit(Point p) {
        if (!(hitList.containsKey(p.getX()) && hitList.containsValue(p.getY()))) {
            hitList.put(p.getX(), p.getY());
            balanceOfHealth(p.getX(), p.getY());
        }
    }

    public void canHit ( int shotPositionX, int shotPositionY){
            if (!(hitList.containsKey(shotPositionX) && hitList.containsValue(shotPositionY))) {
                hitList.put(shotPositionX, shotPositionY);
                balanceOfHealth(shotPositionX, shotPositionY);
            }
    }


    private void setStateShip() {
        if (shipType.getHealth() == 100) {
            state = "All";
        } else if (shipType.getHealth() < 100) {
            state = "Wounded";
        } else  if (shipType.getHealth() == 0) {
            state = "dead";
        }
    }

    private void balanceOfHealth(int shotPositionX, int shotPositionY) {
        int maxHealth = shipType.getDeckerCount();
        if ((position.getX() == shotPositionX) && (position.getY() == shotPositionY)) {
            shipType.setHealth((100 / maxHealth) * shipType.getDeckerCount() - 1);
            int hit = 0;
            if ((position.getX() == shotPositionX) && (position.getY() == shotPositionY)) {
                hit = shipType.getDeckerCount() - 1;
                shipType.setHealth((100 / maxHealth) * hit);
                setStateShip();
            } else {
                shipType.setHealth((100 / maxHealth) * shipType.getDeckerCount());
                setStateShip();
            }
        }
    }

    /**
     * Indicate the coordinates that cannot be used by another neighbor ships.
     *
     * @return Rectangle that outline ships along with adjacent area(near this ship) <br>
     * that cannot be used by another ships.
     */
    public Rectangle outlineTheShipAndItTerritory() {
        Point beginOfSquare = new Point(position.getX() - 1, position.getY() - 1);

        Point endOfSquare = Direction.HORIZONTAL == direction
                ? new Point(position.getX() + shipType.getDeckerCount(), position.getY() + 1)
                : new Point(position.getX() + 1, position.getY() + shipType.getDeckerCount());
        return new Rectangle(beginOfSquare, endOfSquare);

    }

    /**
     * Indicates the only ship itself without adjacent(private) territories.
     *
     * @return the Rectangle that outline ship;
     */
    public Rectangle outlineTheShip() {
        Point beginOfSquare = new Point(position.getX(), position.getY());

        int shipShift = shipType.getDeckerCount() - 1;
        Point endOfSquare = Direction.HORIZONTAL == direction
                ? new Point(position.getX() + shipShift, position.getY())
                : new Point(position.getX(), position.getY() + shipShift);
        return new Rectangle(beginOfSquare, endOfSquare);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.NO_CLASS_NAME_STYLE).append("shipType", shipType)
                .append("name", name)
                .append("state", state)
                .append("health", shipType.getHealth())
                .append("position", position)
                .append("direction", direction)
                .toString();
    }
}
