/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.ui;

import game.Game;
import game.GameState;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Point;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import model.HeroAttributes;
import model.Map;

/**
 *
 * @author rpv0237
 */
public class Excalibur extends javax.swing.JFrame {

    
    /**
     * Creates new form Excalibur
     */
    
    private Game game;
    private MapSquarePanel mapSquarePanel;
    private HeroAttributes hero;
    
    public Excalibur(Game game) {
        
        this.game = game;
        hero = HeroAttributes.saber;
        this.setLocationRelativeTo(null);
        
        initComponents();
        
//        gamePanel.setLayout(new GridLayout(this.game.getWorld().getRowNum(), this.game.getWorld().getColumnNum()));
//
//        for (int i = 0; i < this.game.getWorld().getRowNum(); i++) {
//            for (int j = 0; j < this.game.getWorld().getColumnNum(); j++) {
//                mapSquarePanel = new MapSquarePanel(this.game, i, j);
//                gamePanel.add(mapSquarePanel);
//            }
//        }
        this.update();
    }
    
    public HeroAttributes GetHero(){
        return this.hero;
    }
    
    public void setHero(HeroAttributes hero){
        this.hero = hero;
    }
    
    private void update(){
        
        if(this.game.getGameState().equals(GameState.NotStart)){
            gamePanel.setBackground(Color.white);
//            gamePanel.setLayout(new );
            JButton NewGameButton = new JButton("New Game");
            JButton LoadButton = new JButton("Load A Old Game");
            JButton ExitButton = new JButton("Exit");
            gamePanel.add(NewGameButton);
            gamePanel.add(LoadButton);
            gamePanel.add(ExitButton);
            
            Point p = new Point(100, 100);
            NewGameButton.setLocation(p);
//            LoadButton.setLocation(200, 250);
//            ExitButton.setLocation(300, 350);
            
            
        }else if(this.game.getGameState().equals(GameState.NewGame)){
            gamePanel.setLayout(new GridLayout(3, 2));
            JLabel PlayerName = new JLabel("Player Name : ");
            JTextField name = new JTextField();
            JLabel PlayerType = new JLabel("Role Type : ");
            JComboBox type = new JComboBox();
            for(HeroAttributes h : HeroAttributes.values()){
                type.addItem(h);
            }
            JButton SubmitButton = new JButton("Submit");
            JButton CancelButton = new JButton("Cancel");
            gamePanel.add(PlayerName);
            gamePanel.add(name);
            gamePanel.add(PlayerType);
            gamePanel.add(type);
            gamePanel.add(SubmitButton);
            gamePanel.add(CancelButton);
        }else if(this.game.getGameState().equals(GameState.Start)){
            this.game.startGame();
            gamePanel.setLayout(new GridLayout(this.game.getWorld().getRowNum(), this.game.getWorld().getColumnNum()));
            for (int i = 0; i < this.game.getWorld().getRowNum(); i++) {
                for (int j = 0; j < this.game.getWorld().getColumnNum(); j++) {
                    mapSquarePanel = new MapSquarePanel(this.game, i, j);
                    gamePanel.add(mapSquarePanel);
                }
            }
        }
        
        if(!this.game.getGameState().equals(GameState.NotStart) && !this.game.getGameState().equals(GameState.NewGame) ){
            for (Component component : gamePanel.getComponents()) {
                MapSquarePanel com = (MapSquarePanel) component;
                com.update();
            }
        }
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        gamePanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        gamePanel.setPreferredSize(new java.awt.Dimension(800, 600));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(gamePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(gamePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Excalibur.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Excalibur.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Excalibur.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Excalibur.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        Game game = new Game();
        final Excalibur ui = new Excalibur(game);
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                ui.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel gamePanel;
    // End of variables declaration//GEN-END:variables
}
