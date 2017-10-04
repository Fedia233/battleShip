package com.battleship.components;

import com.battleship.enumeration.Direction;
import com.battleship.enumeration.ShipType;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Ship {

    private Point position;
    private ShipType shipType;
    private Direction direction;

    public Ship() {
    }

    public Ship(int positionX, int positionY, ShipType shipType, Direction direction) {
        this.position = new Point(positionX, positionY);
        this.shipType = shipType;
        this.direction = direction;
    }

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
                .append("position", position)
                .append("direction", direction)
                .toString();
    }
}
