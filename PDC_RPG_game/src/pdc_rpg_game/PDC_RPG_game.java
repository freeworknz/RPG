/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdc_rpg_game;

import controlers.GameStartControler;
import javax.swing.JFrame;

/**
 *
 * @author Robert
 */
public class PDC_RPG_game {
    
    private JFrame gameFrame;
//    private GameStartView gameStart;
//    private NewGameView newGameView;
    
    public PDC_RPG_game(){
        this.gameFrame = new JFrame("PDC_RPG_game");
        this.gameFrame.setSize(1200, 1000);
        this.gameFrame.setLocation(100, 100);
        this.gameFrame.setVisible(true);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        PDC_RPG_game newGame = new PDC_RPG_game();
        
        GameStartView gameStartView = new GameStartView(newGame.gameFrame);
        
        GameStartControler gameStartControler = new GameStartControler(gameStartView);
        
    }
    
    public JFrame getGameFrame(){
        return this.gameFrame;
    }
    
    
}
