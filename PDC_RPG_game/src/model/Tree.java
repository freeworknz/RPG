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
public class Tree extends MapSquare{
    public Tree(Position pos) {
        super(pos, Color.GREEN);
    }
    
    @Override
    public String getStringRepresentation() {
        return "t";
    }
}
