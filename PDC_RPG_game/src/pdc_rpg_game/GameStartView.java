/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdc_rpg_game;

import com.sun.java.swing.plaf.windows.WindowsButtonListener;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author rpv0237
 */
public class GameStartView implements java.util.Observer{

    private JButton startGame;
    private JButton loadGame;
    private JButton exitGame;
    private JPanel gameStartPanel;
    private JFrame newFrame;
    
//    private NewGameView gameView;
    
    public GameStartView(JFrame newFrame){
        this.newFrame = newFrame;
        //this.newFrame.setSize(1200, 1000);
        this.newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        this.gameStartPanel = new JPanel();
        this.gameStartPanel.setOpaque(true);
        this.gameStartPanel.setSize(100, 180);
        this.gameStartPanel.setLocation(500, 350);
        //this.newFrame.add(this.newPanel);
        //this.newPanel.setLocation(100, 100);
        
        this.startGame = new JButton("Start Game");
        this.loadGame = new JButton("Load Game");
        this.exitGame = new JButton("Exit Game");
        
//        this.startGame.addActionListener(new java.awt.event.ActionListener(){
//            @Override
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                StartActionPerformed(evt);
//            }
//        });
        
        
        this.startGame.setSize(100, 60);
        this.loadGame.setSize(100, 60);
        this.exitGame.setSize(100, 60);
        this.gameStartPanel.add(this.startGame);
        this.gameStartPanel.add(this.loadGame);
        this.gameStartPanel.add(this.exitGame);
        
        this.newFrame.add(this.gameStartPanel, BorderLayout.SOUTH);
        //this.gameStartPanel.setBounds(500, 350, 100, 180);
        //this.newFrame.setContentPane(newPanel);
        //this.newFrame.setLocationByPlatform(true);
        this.newFrame.setVisible(true);
    }
    
    @Override
    public void update(Observable o, Object arg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public JFrame getFrame(){
        return this.newFrame;
    }
    
    public void removePanel(){
        this.gameStartPanel.setVisible(false);
        this.newFrame.remove(this.gameStartPanel);
    }
    
    public void exit(){
        System.exit(10);
    }
    
    public void setActionListener(ActionListener c){
        this.startGame.addActionListener(c);
        this.loadGame.addActionListener(c);
        this.exitGame.addActionListener(c);
    }
    
    
//    private void StartActionPerformed(java.awt.event.ActionEvent evt) {                                      
//        // TODO add your handling code here:
//        this.newFrame.remove(this.gameStartPanel);
//        NewGameView gameView = new NewGameView(this.newFrame);
////        this.newFrame = gameView.getFrame();
////        System.out.println("xxxxxxxxxxxxx");
//    }
}
