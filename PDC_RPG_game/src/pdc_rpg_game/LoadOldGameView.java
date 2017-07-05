/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdc_rpg_game;


import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.Observable;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

/**
 *
 * @author rpv0237
 */
public class LoadOldGameView implements java.util.Observer{
   
    private JFrame newFrame;
    private JPanel loadPanel;
    private JLabel select;
    private JList playersList;
    private JButton submit;
    private JButton cancel;
    
    public LoadOldGameView(JFrame newFrame){
        
        this.newFrame = newFrame;
        this.loadPanel = new JPanel();
        this.select = new JLabel("Please select an old game:  ");
        this.playersList = new JList();
        this.submit = new JButton("submit");
        this.cancel = new JButton("cancel");
        
        
        
        
        
    }
    
    public void setActionListener(ActionListener e){
        this.submit.addActionListener(e);
        this.cancel.addActionListener(e);
    }
    
    public void setList(JList playersList){
        this.playersList = playersList;
        this.loadPanel.add(this.select, new BorderLayout().CENTER);
        this.loadPanel.add(playersList, new BorderLayout().CENTER);
        this.loadPanel.add(this.submit, new BorderLayout().SOUTH);
        this.loadPanel.add(this.cancel, new BorderLayout().SOUTH);
        this.newFrame.add(this.loadPanel);
    }
    
    public JList getList(){
        return this.playersList;
    }
    
    public JFrame getFrame(){
        return this.newFrame;
    }

    @Override
    public void update(Observable o, Object arg) {
        
    }
}
