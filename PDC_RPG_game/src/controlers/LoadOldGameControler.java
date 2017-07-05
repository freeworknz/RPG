/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlers;

import static controlers.NewGameControler.url;
import game.Game;
import game.GameState;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JList;
import model.Map;
import model.Player;
import model.Skill;
import model.Weapon;
import pdc_rpg_game.LoadOldGameView;
import pdc_rpg_game.PalyGameView;

/**
 *
 * @author rpv0237
 */
public class LoadOldGameControler implements java.awt.event.ActionListener{
    
    private LoadOldGameView loadOldGame;
    private Game game;
    
    
    public LoadOldGameControler(LoadOldGameView loadOldGame){
        this.loadOldGame = loadOldGame;
        this.loadOldGame.setList(this.setJlist());
        this.game = new Game();
    }
    
    public JList setJlist(){
        int i = 0;
        for(Player p : this.getLoadPlayer()){
            if(!p.equals(null)){
                i++;
            }
        }
        Player [] ps = new Player [i];
        i = 0;
        for(Player p : this.getLoadPlayer()){
            if(!p.equals(null)){
                ps[i] = p;
            }
        }
        JList playersList = new JList(ps);
        return playersList;
    }
    
    public Player [] getLoadPlayer(){
        Player [] players = new Player[100];
        
        try{
            Connection conn = DriverManager.getConnection(url);
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("select * from Player");
            int i = 0;
            while(rs.next()){
                players[i].setName(rs.getString(2));
                players[i].setMaxHp(rs.getInt(3));
                players[i].setHp(rs.getInt(4));
                players[i].setMaxMp(rs.getInt(5));
                players[i].setMp(rs.getInt(6));
                players[i].setAtk(rs.getInt(7));
                players[i].setDef(rs.getInt(8));
                players[i].setLv(rs.getInt(9));
                players[i].setExp(rs.getInt(10));
                players[i].setMoney(rs.getInt(11));
                players[i].setWeapon(Weapon.values()[rs.getInt(11)]);
                players[i].addSkill(Skill.values()[rs.getInt(12)]);
                players[i].addSkill(Skill.values()[rs.getInt(12)+1]);
                players[i].addSkill(Skill.values()[rs.getInt(12)+2]);
                i++;
            }
            statement.close();
            conn.close();
        }catch(SQLException e){
            System.err.println(e);
        }
        
        return players;
    }
    
    public Game getLoadGame(Player player){
        GameState state = null;
        Map map = null;
        try {
            Connection conn = DriverManager.getConnection(url);
            Statement statement = conn.createStatement();
            
            ResultSet rs = statement.executeQuery("select P_id from Player where P_name = " + player.getName());
            int id = rs.getInt(1);
            rs = statement.executeQuery("select * from Game where P_id = " + id);
            state = GameState.valueOf(rs.getString(3));
            map = new Map(rs.getString(4));
            statement.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(LoadOldGameControler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return new Game(map, player, state);
    }
    
    public Game getGame(){
        return this.game;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("submit")){
            this.game = this.getLoadGame((Player)this.loadOldGame.getList().getSelectedValue());
            PlayGameControler playGameControler = new PlayGameControler(new PalyGameView(this.loadOldGame.getFrame()), this.game);
        }else if(e.getActionCommand().equals("cancel")){
            
        }
    }
    
}
