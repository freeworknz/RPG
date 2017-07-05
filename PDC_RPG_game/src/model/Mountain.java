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
public class Mountain extends MapSquare{
    public Mountain(Position pos) {
        super(pos, Color.DARK_GRAY);
    }
    
    @Override
    public String getStringRepresentation() {
        return "M";
    }
    
}
