/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import game.Direction;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author dixon
 */
public class Map {
    // rows and columns of all maps
    private final int rows = 30;
    private final int columns = 30;
    // 2D array of map square 
    private MapSquare[][] squares;
    private String fileName;
    /**
     * Constructs an empty map.
     *
     * @param fileName name of the map
     *
     */
    public Map(String fileName){
        this.fileName = fileName;
        try (Scanner input = new Scanner(new File(fileName))) {
            initialiseMap();
            setUpSquares(input);
            input.close();
        } catch (FileNotFoundException e) {
            System.err.println("Unable to find data file '" + fileName + "'");
        }
    }
    
    public int getRowNum(){
        return this.rows;
    }
    
    public int getColumnNum(){
        return this.columns;
    }
    
    /**
     * Initialises a 30*30 map.
     * All squares are initialised to sand squares.
     */
    private void initialiseMap() {
        squares = new MapSquare[30][30];
        for (int row = 0; row < 30; row++) {
            for (int col = 0; col < 30; col++) {
                Position pos = new Position(this, row, col);
                squares[row][col] = new Sand(pos);
            }
        }
    }
    
    /**
     * Creates a square at given position for this squareID.
	 *
	 * @param row the row to create this square
	 * @param column the column to create this square
	 * @param squareID the ID on this square
	 *
	 * @return the created BoardSquare instance or null if not possible
     */
    private MapSquare createSquare(int row, int col, char squareID) {
        MapSquare square = null;
        Position pos = new Position(this, row, col);
        switch (squareID) {
            case '.':
                square = new Sand(pos);
                break;
            case 'M':
                square = new Mountain(pos);
                break;
            case 'B':
                square = new Building(pos);
                break;
            case 'P':
                square = new Portal(pos);
                break;
            case 'r':
                square = new Rock(pos);
                break;
            case 't':
                square = new Tree(pos);
                break;
            case 'w':
                square = new Water(pos);
                break;
            case 'X':
                square = new EmptySquare(pos);
                square.addOccupant(new Xman(pos));
                break;
//            case 'H':
//                square = new EmptySquare(pos);
//                square.addOccupant(new Player());
//                break;
//            case 'Z':
//                square = new EmptySquare(pos);
//                //square.addOccupant(new Monster());
//                if(this.fileName.equals("area_x.txt")){
//                    square.addOccupant(new Monster(MonsterAttributes.MONSTERA));
//                }else if(this.fileName.equals("area_y.txt")){
//                    square.addOccupant(new Monster(MonsterAttributes.MONSTERB));
//                }
//                break;
            case 'O':
                square = new EmptySquare(pos);
                if(this.fileName.equals("area_x.txt")){
                    square.addOccupant(new Boss(MonsterAttributes.BOSSA));
                }else if(this.fileName.equals("area_y.txt")){
                    square.addOccupant(new Boss(MonsterAttributes.BOSSB));
                }else if(this.fileName.equals("area_z.txt")){
                    square.addOccupant(new Boss(MonsterAttributes.BOSSC));
                }
                break;
        }
        return square;
    }
    
    /**
     * Creates the array of squares that are represented on the map in the file.
     *
     * @param input the scanner input from the data file
     */
    private void setUpSquares(Scanner input) {
        for (int row = 0; row < 30; row++) {
            String boardRow = input.next();
            for (int col = 0; col < 30; col++) {
                char squareID = boardRow.charAt(col);
                MapSquare square = createSquare(row, col, squareID);
                if (square != null) {
                    squares[row][col] = square;
                }
            }
        }
    }
    
     /**
     * Gets the square at this given position.
	 *
     * @param position the position of square
	 *
     * @return BoardSquare at this position
     */
    public MapSquare getSquare(Position pos) {
        return squares[pos.getRow()][pos.getColumn()];
    }
    
    /**
     * Prints out the map.
     */
    public void printMap() {
        for (MapSquare[] row : squares) {
            for (MapSquare square : row) {
                String str = square.getStringRepresentation();
                String occ = square.getOccupantStringRepresentation();
                String output = "";
                if(occ.equals("")) {
                    output = str;
                }
                output += occ;
                System.out.print(output);
            }
            System.out.println();
        }
    }
    
    public MapSquare getEmptyRandomSquare()
    {
        MapSquare square;
        do
        {
            // generate random position
            Position pos = new Position(this, 
                (int) (Math.random() * rows), 
                (int) (Math.random() * columns));
            square = getSquare(pos);
            // repeat as long as square at that position is not empty
        } while ( !(square instanceof Sand ) ||
                    square.hasOccupants() );
        
        return square;
    }
    
    public MapSquare [][] getMapSquare(){
        return this.squares;
    }
    
    public Position calculateDestination(Position from, Direction moveDirection){
        Position destination = null;

        try
        {
            // get the from row/column
            int row = from.getRow();
            int col = from.getColumn();
            
            // "blindly" calculate the destination row/column
            switch ( moveDirection )
            {
                case NORTH: row--; break;
                case EAST:  col++; break;
                case SOUTH: row++; break;
                case WEST:  col--; break;
            }
        
            // create the destination position - this might fail
            destination = new Position(from.getMap(), row, col);
        }
        catch (NullPointerException | IllegalArgumentException e)
        {
            // either from was null, or destination is invalid
            // -> do nothing and thus return null position
        }
        
        // check if destination is a wall
        if( (destination != null) && !(getSquare(destination) instanceof Sand || getSquare(destination) instanceof Portal || 
                getSquare(destination).hasOccupants()) )
        {
            // if so, don't go there
            destination = null;
        }
        
        return destination;
    }
    
    public String getFileName(){
        return this.fileName;
    }
}
