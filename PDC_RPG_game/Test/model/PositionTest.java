/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author dixon
 */
public class PositionTest {

    private Map map;
    private Position position;
    private int row;
    private int col;

    @Before
    public void setUp() {
        map = new Map("town.txt");
        row = 1;
        col = 1;
        position = new Position(map, row, col);
    }

    @After
    public void tearDown() {
        map = null;
        position = null;
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNewPositionInvalidBoard() throws IllegalArgumentException {
        Position invalid = new Position(null, 1, 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNewPositionRowTooSmall() throws IllegalArgumentException {
        Position invalid = new Position(map, -1, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNewPositionRowTooLarge() throws IllegalArgumentException {
        Position invalid = new Position(map, 30, 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNewPositionColumnTooSmall() throws IllegalArgumentException {
        Position invalid = new Position(map, 1, -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNewPositionColumnTooLarge() throws IllegalArgumentException {
        Position invalid = new Position(map, 1, 30);
    }

    @Test
    public void testGetRow() {
        assertEquals("Incorrect row", row, position.getRow());
    }

    @Test
    public void testGetColumn() {
        assertEquals("Incorrect column", col, position.getColumn());
    }
}
