/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdc_rpg_game;

import static controlers.NewGameControler.url;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Observable;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import model.HeroAttributes;

/**
 *
 * @author rpv0237
 */
public class NewGameView implements java.util.Observer{
    
    private JFrame newFrame;
    private JPanel namePanel;
    private JPanel buttonPanel;
    private JLabel nameLabel;
    private JTextField nameArea;
    private JLabel occupation;
    private JComboBox occupations;
    private JButton submit;
    private JButton chancel;
    
    
    public NewGameView(JFrame newFrame){
        this.newFrame = newFrame;
        this.namePanel = new JPanel(new FlowLayout());
        this.buttonPanel = new JPanel(new FlowLayout());
        
        // name panel
        this.nameLabel = new JLabel("Player Name:");
        this.nameArea = new JTextField("Please input your name here!");
        this.occupation = new JLabel("Player Occupation:");
        this.occupations = new JComboBox();
        this.occupations.addItem(HeroAttributes.saber);
        this.occupations.addItem(HeroAttributes.lancer);
        this.occupations.addItem(HeroAttributes.caster);
        this.namePanel.add(this.nameLabel);
        this.namePanel.add(this.nameArea);
        this.namePanel.add(this.occupation);
        this.namePanel.add(this.occupations);
        
        // button panel
        this.submit = new JButton("Submit");
        this.chancel = new JButton("Chancel");
        this.buttonPanel.add(this.submit);
        this.buttonPanel.add(this.chancel);
//        this.newFrame.setLayout(new FlowLayout());
        this.newFrame.add(this.namePanel, BorderLayout.CENTER);
        this.newFrame.add(this.buttonPanel, BorderLayout.SOUTH);
        this.nameArea.setSize(100, 20);
        this.newFrame.setVisible(true);
    }
    
    public JFrame getFrame(){
        return this.newFrame;
    }
    
    public void setControler(ActionListener e){
        this.submit.addActionListener(e);
        this.chancel.addActionListener(e);
    }
    
    public void removepanels(){
        this.namePanel.setVisible(false);
        this.buttonPanel.setVisible(false);
        this.newFrame.remove(this.namePanel);
        this.newFrame.remove(this.buttonPanel);
    }
    
    public void storePlayerInformation(HeroAttributes hero, String playerName){
        
        try{
            
            Connection conn = DriverManager.getConnection(url);
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("select * from Player");
            int i = 0;
            while(rs.next()){
                i++;
            }
            PreparedStatement ps = conn.prepareStatement("insert into Player values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setInt(1, i+1);
            ps.setString(2, playerName);      
            ps.setInt(3, hero.getMaxHp());
            ps.setInt(4, hero.getHp());
            ps.setInt(5, hero.getMaxMp());
            ps.setInt(6, hero.getMp());
            ps.setInt(7, hero.getAtk());
            ps.setInt(8, hero.getDef());
            ps.setInt(9, hero.getLv());
            ps.setInt(10, hero.getExp());
            ps.setInt(11, hero.getMoney());
            ps.setInt(12, 1);
            ps.setInt(13, 1);
            ps.setTime(14,null);
            ps.execute();
            
            rs.close();
            statement.close();
            conn.close();
        }catch(SQLException e){
            System.err.println(e);
        }
    }
    
    public HeroAttributes getComboItem(){
        return (HeroAttributes) this.occupations.getSelectedItem();
    }
    
    public String getTxt(){
        return this.nameArea.getText();
    }
    
    @Override
    public void update(Observable o, Object arg) {
        this.newFrame.setVisible(true);
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
