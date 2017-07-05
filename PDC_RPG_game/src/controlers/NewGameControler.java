/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlers;

import java.awt.event.ActionEvent;
import pdc_rpg_game.GameStartView;
import pdc_rpg_game.NewGameView;
import pdc_rpg_game.PalyGameView;
import controlers.PlayGameControler;
import game.Game;
import game.GameState;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import model.HeroAttributes;
import model.Map;
import model.Player;



/**
 *
 * @author rpv0237
 */
public class NewGameControler implements java.awt.event.ActionListener{
    
    private NewGameView newGameView;
    private Game newGame;
    public static String url = "jdbc:derby://localhost:1527/RPG_game";
    
    
    public NewGameControler(NewGameView newGameView){
        this.newGameView = newGameView;
        this.newGameView.setControler(this);
        this.newGame = new Game();
        
        this.newGame.startGame();
//        try{
//            Connection conn = DriverManager.getConnection(url);
//        }catch(SQLException ex){
//            System.err.println(ex);
//        }
        
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
        try{
            String str = e.getActionCommand();
            switch (str) {
                case "Submit":
                    this.newGame = new Game(new Map("town.txt"), new Player(this.newGameView.getComboItem(), this.newGameView.getTxt()), GameState.Start);
                    this.newGame.startGame();
                    
//                    Game game = new Game(new Map("town.txt"), new Player(this.newGameView.getComboItem(), this.newGameView.getTxt()), GameState.Start);
//                    this.newGame.setPlayer(new Player(this.newGameView.getComboItem(), this.newGameView.getTxt()));
                    
                    //PalyGameView palyGameView = new PalyGameView(this.newGameView.getFrame());
                    this.newGameView.storePlayerInformation(this.newGameView.getComboItem(), this.newGameView.getTxt());
                    PlayGameControler palyGameControler = new PlayGameControler(new PalyGameView(this.newGameView.getFrame()), this.newGame);
                    this.newGameView.removepanels();
                    
                    
                    break;
                case "Chancel":
                    this.newGameView.removepanels();
//                    GameStartView gameStartView = new GameStartView(this.newGameView.getFrame());
                    GameStartControler gameStartControler = new GameStartControler(new GameStartView(this.newGameView.getFrame()));
                    break;
            }
        }catch(Exception ev){
            System.err.println(ev);
        }
        
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
//    public void storePlayerInformation(HeroAttributes hero, String playerName){
//        
//        try{
//            Statement statement = conn.createStatement();
//            statement.executeQuery("inster into Player values (" + (statement.getMaxRows() + 1) + playerName + hero.getMoney() + hero.getLv() +
//                    hero.getHp() + hero.getMaxHp() + hero.getMp() + hero.getMaxMp() + hero.getExp() + hero.getAtk() + hero.getDef() + 1 + 1 + ");");
//        }catch(SQLException e){
//            System.err.println(e);
//        }
//    }
    
}
