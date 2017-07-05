/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author dixon
 */
public class Position {

    private Map map;
    private int row;
    private int column;

    public Position(Map map, int row, int column) throws IllegalArgumentException {
        if (map == null) {
            throw new IllegalArgumentException(
                    "Cannot create a map position without a map reference.");
        }
        int maxRow = 29;
        if ((row < 0) || (row > maxRow)) {
            throw new IllegalArgumentException(
                    row + " is invalid. Must be between 0 to "
                    + maxRow + " inclusive.");
        }
        int maxCol = 29;
        if ((column < 0) || (column > maxCol)) {
            throw new IllegalArgumentException(
                    column + " is invalid. Must be between 0 to "
                    + maxCol + " inclusive.");
        }
        this.row = row;
        this.column = column;
        this.map = map;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public Map getMap() {
        return map;
    }

    @Override
    public boolean equals(Object other) {
        boolean equals = false;
        if (other == this) {
            equals = true;
        } else if (other instanceof Position) {
            Position pos = (Position) other;
            equals = (pos.row == this.row)
                    && (pos.column == this.column)
                    && (pos.map == this.map);
        }
        return equals;
    }
}
