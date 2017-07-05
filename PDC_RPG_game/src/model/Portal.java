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
public class Portal extends MapSquare{
    public Portal(Position pos) {
        super(pos, Color.cyan);
    }
    
    @Override
    public String getStringRepresentation() {
        return "P";
    }
}
