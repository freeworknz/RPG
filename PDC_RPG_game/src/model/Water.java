/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.awt.Color;

/**
 *
 * @author dixon
 */
public class Water extends MapSquare{
    public Water(Position pos) {
        super(pos, Color.BLUE);
    }
    
    @Override
    public String getStringRepresentation() {
        return "w";
    }
}
