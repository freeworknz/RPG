/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdc_rpg_game;

import static controlers.NewGameControler.url;
import game.Game;
import game.GameState;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.HeroAttributes;
import model.Player;
import model.ui.Conversation;
import model.ui.MapSquarePanel;

/**
 *
 * @author rpv0237
 */
public class PalyGameView implements java.util.Observer{
    
    private JFrame newFrame;
    private JPanel gamePanel;
    private JPanel controlPanel;
    
    private JPanel system_content;
    private JPanel information_content;
    private JPanel bag_content;
    private Conversation conversation_content;
    
    private JButton system;
    private JButton information;
    private JButton bag;
    private JButton backGame;
    private JButton saveGame;
    private JButton loadGame;
    
    
    public PalyGameView(JFrame newFrame){
    
        this.newFrame = newFrame;
        this.gamePanel = new JPanel(new GridLayout(30, 30));
        
        // control panel
        
        this.controlPanel = new JPanel(new GridLayout(1, 3));
        this.system = new JButton("system");
        this.information = new JButton("information");
        this.bag = new JButton("bag");
        this.controlPanel.add(this.system);
        this.controlPanel.add(this.information);
        this.controlPanel.add(this.bag);
        
        // system panel
        
        this.system_content = new JPanel(new FlowLayout());
        this.backGame = new JButton("Back to game");
        this.saveGame = new JButton("Save game");
        this.loadGame = new JButton("Load game");
        this.system_content.add(this.backGame);
        this.system_content.add(this.saveGame);
        this.system_content.add(this.loadGame);
        
        // information panel
        
        this.information_content = new JPanel(new GridLayout(11, 2));
        
        
        // bag panel
        
        this.bag_content = new JPanel();
        
        // conversation panel
        
        this.conversation_content = new Conversation();
        
        // add panels
        
        this.newFrame.add(this.gamePanel, new BorderLayout().CENTER);
        this.newFrame.add(this.controlPanel, new BorderLayout().SOUTH);
        
        this.newFrame.setVisible(true);
    }
    
    
    public void addComponents(Component com){
        this.gamePanel.add(com);
    }
    
    public void setActionListener(ActionListener e){
        this.system.addActionListener(e);
        this.information.addActionListener(e);
        this.bag.addActionListener(e);
        this.backGame.addActionListener(e);
        this.loadGame.addActionListener(e);
        this.saveGame.addActionListener(e);
        this.conversation_content.setActionListener(e);
    }
    
    public void setKeyPressListener(KeyListener e){
        this.newFrame.addKeyListener(e);
        this.conversation_content.addKeyListener(e);
        this.newFrame.setFocusable(true);
        this.conversation_content.setFocusable(true);
    }
    
//    
//    public void addKeyProformListener(java.awt.event.KeyAdapter e){
//        this.gamePanel.addKeyListener(e);
//    }
    
    public void update(){
        
        for (Component component : this.gamePanel.getComponents()) {
            MapSquarePanel com = (MapSquarePanel) component;
            com.update();
        }
        
        
    }
    
    public void displaySystemPanel(){
        this.gamePanel.setVisible(false);
        this.newFrame.add(this.system_content, new BorderLayout().CENTER);
        this.system_content.setVisible(true);
    }
     
    public void undisplaySystemPanel(){
        this.system_content.setVisible(false);
        this.newFrame.remove(this.system_content);
        this.gamePanel.setVisible(true);
    }
    
    public void displayInformationPanel(){
        this.gamePanel.setVisible(false);
        this.newFrame.add(this.information_content, new BorderLayout().CENTER);
        this.information_content.setVisible(true);
    }
    
    public void undisplayInformationPanel(){
        this.information_content.setVisible(false);
        this.information_content = new JPanel(new GridLayout(10, 2));
        this.newFrame.remove(this.information_content);
        this.gamePanel.setVisible(true);
    }
    
    public void displayBagPanel(){
        this.gamePanel.setVisible(false);
        this.newFrame.add(this.bag_content, new BorderLayout().CENTER);
        this.bag_content.setVisible(true);
    }
    
    public void undisplayBagPanel(){
        this.bag_content.setVisible(false);
        this.newFrame.remove(this.bag_content);
        this.gamePanel.setVisible(true);
    }
    
    public void displayConversationPanel(String str){
        this.gamePanel.setVisible(false);
        this.conversation_content.setLabelContent(str);
        this.newFrame.add(this.conversation_content, new BorderLayout().CENTER);
        this.conversation_content.setVisible(true);
    }
    
    public void undisplayConversationPanel(){
        this.conversation_content.setVisible(false);
        this.newFrame.remove(this.conversation_content);
        this.gamePanel.setVisible(true);
    }
    
    public String getPlayerName(){
        String str = "";
        try{
            Connection conn = DriverManager.getConnection(url);
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("select P_name from Player");
            while(rs.next()){
                str = rs.toString();
            }
            statement.close();
            conn.close();
        }catch(SQLException e){
            System.err.println(e);
        }
        return str;
    }
    
    public JFrame getFrame(){
        return this.newFrame;
    }
    
    public boolean checkGamePanelVisiable(){
        return this.gamePanel.isVisible();
    }
    
    public void displayPlayerInformation(Game game){
        for(String str : game.getPlayerInformation()){
            this.information_content.add(new JLabel(str));
        }
    }
    
    public void undisplay(){
        this.gamePanel.setVisible(false);
        this.newFrame.remove(gamePanel);
    }
    
    @Override
    public void update(Observable o, Object arg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
