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
public class MapTest {
    private Map map;
    private MapSquare square;
    private Position position;
    
    @Before
    public void setUp() {
        map = new Map("town.txt");
    }
    
    @After
    public void tearDown() {
        map = null;
    }
    
    @Test
    public void testGetSquare()
    {
        position = new Position(map,1,1);
        square = map.getSquare(position);
        assertEquals(position, square.getPosition());
    }
}
