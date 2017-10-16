package com.battleship.components;

import com.battleship.enumeration.Direction;
import com.battleship.enumeration.ShipState;
import com.battleship.enumeration.ShipType;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Ship {

    private final static int STATION_REFERENCE = 100;
    private String name;
    private Point position;
    private Direction direction;
    private ShipType shipType;
    private ShipState shipState;
    private HashSet<Point> hitList = new HashSet<>();

    public Ship() {
    }

    public Ship(int positionX, int positionY, ShipType shipType, Direction direction) {
        this.name = "Ship " + positionX + " " + positionY + " ";
        this.position = new Point(positionX, positionY);
        this.shipType = shipType;
        this.shipState = ShipState.PERFECT;
        this.direction = direction;
        shipType.setHealth(100);
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

    public void canHit(Point p) {
        if (!(hitList.contains(p))) {
            if ((position.getX() == p.getX()) && (position.getY() == p.getY())) {
                hitList.add(p);
                balanceOfHealth();
            }
        }
    }

    private void setStateShip() {
        if (shipType.getHealth() == STATION_REFERENCE) {
            shipState = ShipState.PERFECT;
        } else if (shipType.getHealth() < STATION_REFERENCE  && shipType.getHealth() > 0) {
           shipState = ShipState.WOUNDED;
        } else  if (shipType.getHealth() == 0) {
           shipState = ShipState.DEAD;
        }
    }

    private void balanceOfHealth() {
        shipType.setHealth((int)(100.0 / shipType.getDeckerCount()) * (shipType.getDeckerCount() - 1));
        setStateShip();
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
                .append("state", shipState)
                .append("health", shipType.getHealth())
                .append("position", position)
                .append("direction", direction)
                .toString();
    }
}
