package com.battleship;

import static com.battleship.enumeration.ShipType.DOUBLE_DECKER;
import static com.battleship.enumeration.ShipType.FOUR_DECKER;
import static com.battleship.enumeration.ShipType.SINGLE_DECKER;
import static com.battleship.enumeration.ShipType.THREE_DECKER;

import java.util.stream.Stream;

import com.battleship.components.Field;
import com.battleship.components.Point;
import com.battleship.components.Ship;
import com.battleship.enumeration.ShipType;
import com.battleship.generator.ShipGenerator;
import com.battleship.printer.ConsoleFieldPrinter;

public class Game {
    private static final int DEFAULT_FIELD_SIZE = 10;
    private static final int COUNT_OF_FOUR_DECKER_SHIPS = 1;
    private static final int COUNT_OF_THREE_DECKER_SHIPS = 2;
    private static final int COUNT_OF_DOUBLE_DECKER_SHIPS = 3;
    private static final int COUNT_OF_SINGLE_DECKER_SHIPS = 4;

    public static void main(String[] args) {
        Field field = new Field(DEFAULT_FIELD_SIZE);

        ShipGenerator shipGenerator = new ShipGenerator(field);

        // generate ships
        generateShipsAndPutOnTheField(field, FOUR_DECKER, COUNT_OF_FOUR_DECKER_SHIPS, shipGenerator);
        generateShipsAndPutOnTheField(field, THREE_DECKER, COUNT_OF_THREE_DECKER_SHIPS, shipGenerator);
        generateShipsAndPutOnTheField(field, DOUBLE_DECKER, COUNT_OF_DOUBLE_DECKER_SHIPS, shipGenerator);
        generateShipsAndPutOnTheField(field, SINGLE_DECKER, COUNT_OF_SINGLE_DECKER_SHIPS, shipGenerator);

        // print out the field with ships to the console
        ConsoleFieldPrinter consoleFieldPrinter = new ConsoleFieldPrinter(field);

        consoleFieldPrinter.print();

    }

    private static void generateShipsAndPutOnTheField(Field field, ShipType shipType, int countOfShips, ShipGenerator shipGenerator) {
        Stream.iterate(0, i -> i).limit(countOfShips).forEach(o -> {
            Ship ship = shipGenerator.generate(shipType);
            field.addShip(ship);
        });
    }

}
