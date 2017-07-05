/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.Color;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author dixon
 */
public abstract class MapSquare {

    // maximum number of coccupants per map square
    public static final int MAX_OCCUPANTS = 2;
    // the position of the board square
    private Position position;
    // the colour to use when drawing the square    
    private Color colour;
    // the occupants of the square
    private Set<Occupant> occupants;

    /**
     * Creates a new map square.
     * 
     * @param pos     the position of the map square
     * @param colour  the colour of the square
     */
    public MapSquare(Position pos, Color colour)
            throws IllegalArgumentException {
        if (pos == null) {
            throw new IllegalArgumentException(
                    "Cannot create a square without a position.");
        }
        if (colour == null) {
            throw new IllegalArgumentException(
                    "Cannot create a square without a colour.");
        }
        this.position = pos;
        this.colour = colour;
        this.occupants = new HashSet<>();
    }

    public Position getPosition() {
        return position;
    }

    public Color getColour() {
        return colour;
    }

    public boolean addOccupant(Occupant occ) {
        boolean success = false;
        if ((occ != null) && (occupants.size() < MAX_OCCUPANTS)) {
            success = occupants.add(occ);
            if (success) {
                occ.setPosition(this.position);
            }
        }
        return success;
    }

    public boolean removeOccupant(Occupant occ) {
        boolean removed = false;
        if (occ != null) {
            removed = occupants.remove(occ);
            if (removed) {
                occ.setPosition(null);
            }
        }
        return removed;
    }

    public Occupant[] getOccupantsAsArray() {
        return occupants.toArray(new Occupant[occupants.size()]);
    }

    public boolean hasOccupants() {
        return !occupants.isEmpty();
    }

    public String getOccupantStringRepresentation() {
        String occRep = "";
        for (Occupant occ : occupants) {
            occRep += occ.getStringRepresentation();
        }
        return occRep;
    }

    public abstract String getStringRepresentation();
}
