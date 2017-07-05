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
public abstract class Occupant {

    // the position of the occupant (null, when not on the map)
    private Position position;

    /**
     * Creates a new Occupant instance without a position.
     */
    public Occupant() {
        this.position = null;
    }

    public void setPosition(Position pos) {
        position = pos;
    }

    public Position getPosition() {
        return position;
    }

    public abstract String getStringRepresentation();

}
