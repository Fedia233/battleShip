package com.battleship.printer;

import com.battleship.components.Field;
import com.battleship.components.Point;
import com.battleship.components.Ship;
import com.battleship.enumeration.Direction;

import java.util.Arrays;
import java.util.List;

import static com.battleship.enumeration.Direction.HORIZONTAL;
import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.commons.lang3.StringUtils.SPACE;

public class ConsoleFieldPrinter {

    private static final char HEADER_INITIAL_CHARACTER = 'A';

    // unicode symbol of filled square
    private static final char SHIP_BLOCK_CHARACTER = 9641;

    private static final String SINGLE_CELL_FORMAT = " %s";
    private static final String LEFT_AXIS_FORMAT = "%2s |";

    private Field field;

    public ConsoleFieldPrinter(Field field) {
        this.field = field;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public void print() {
        int fieldSize = field.getFieldSize();
        printHeader(fieldSize);

        String[][] shipsInArrayView = mapShipsToArray(field);

        for (int i = 0; i < shipsInArrayView.length; i++) {
            System.out.print(String.format(LEFT_AXIS_FORMAT, i + 1));
            for (int j = 0; j < shipsInArrayView[i].length; j++) {
                System.out.print(String.format(SINGLE_CELL_FORMAT, shipsInArrayView[i][j]));
            }
            System.out.println();

        }
    }

    private void printHeader(int headerLength) {
        char charToPrint = HEADER_INITIAL_CHARACTER;
        System.out.print(String.format(LEFT_AXIS_FORMAT, EMPTY));
        for (int i = 0; i < headerLength; i++) {
            System.out.print(String.format(SINGLE_CELL_FORMAT, charToPrint++));
        }
        System.out.println();
    }


    private String[][] mapShipsToArray(Field field) {
        int fieldSize = field.getFieldSize();
        String[][] fieldArray = new String[fieldSize][fieldSize];
        for (String[] anArr : fieldArray) {
            Arrays.fill(anArr, SPACE);
        }

        List<Ship> ships = field.getShips();
        for (Ship ship : ships) {
            int shipLength = ship.getShipType().getDeckerCount();
            Direction direction = ship.getDirection();
            Point position = ship.getPosition();
            int x = position.getX();
            int y = position.getY();
            for (int i = 0; i < shipLength; i++) {
                fieldArray[y][x] = String.valueOf(SHIP_BLOCK_CHARACTER);
                if (direction == HORIZONTAL) {
                    x++;
                } else {
                    y++;
                }
            }
        }
        return fieldArray;
    }
}
