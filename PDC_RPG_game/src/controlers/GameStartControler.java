/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlers;

import java.awt.event.ActionEvent;
import pdc_rpg_game.GameStartView;
import pdc_rpg_game.LoadOldGameView;
import pdc_rpg_game.NewGameView;

/**
 *
 * @author rpv0237
 */
public class GameStartControler implements java.awt.event.ActionListener{

    private GameStartView gameStartView;
//    private NewGameView newGameView;
    
    public GameStartControler(GameStartView gameStartView){
        this.gameStartView = gameStartView;
        this.gameStartView.setActionListener(this);
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        try{
            String str = e.getActionCommand();
            switch (str) {
                case "Start Game":
                    this.gameStartView.removePanel();
                    NewGameControler newGameControler = new NewGameControler(new NewGameView(this.gameStartView.getFrame()));
                    break;
                case "Load Game":
                    this.gameStartView.removePanel();
                    LoadOldGameControler loadOldGameControler = new LoadOldGameControler(new LoadOldGameView(this.gameStartView.getFrame()));
                    break;
                case "Exit Game":
                    this.gameStartView.exit();
                    break;
            }
        }catch(Exception ev){
            System.err.println(ev);
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
