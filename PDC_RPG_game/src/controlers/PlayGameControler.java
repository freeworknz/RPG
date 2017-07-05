/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlers;


import game.Direction;
import game.Game;
import game.GameState;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import model.HeroAttributes;
import model.Portal;
import model.Position;
//import model.ui.Fight;
import model.ui.MapSquarePanel;
import pdc_rpg_game.PalyGameView;

/**
 *
 * @author rpv0237
 */
public class PlayGameControler implements java.awt.event.ActionListener,java.awt.event.KeyListener{
    
    private PalyGameView playGameView;
    private Game newGame;
    private MapSquarePanel mapSquarePanel;
    private HeroAttributes hero;
    private Direction direction;
    
    public PlayGameControler(PalyGameView playGameView, Game newGame){
        this.newGame = newGame;
        this.playGameView = playGameView;
        this.playGameView.setActionListener(this);
        this.playGameView.setKeyPressListener(this);
        this.direction = Direction.SOUTH;
        
        for (int i = 0; i < this.newGame.getWorld().getRowNum(); i++) {
            for (int j = 0; j < this.newGame.getWorld().getColumnNum(); j++) {
                this.mapSquarePanel = new MapSquarePanel(this.newGame, i, j);
                this.playGameView.addComponents(mapSquarePanel);
            }
        }
        
        this.playGameView.update();
    }
    
    
    

    @Override
    public void actionPerformed(ActionEvent e) {
        String str = e.getActionCommand();
        if(str.equals("system")){
            if(this.playGameView.checkGamePanelVisiable()){
                this.playGameView.displaySystemPanel();
            }else{
                this.playGameView.undisplaySystemPanel();
            }
        }else if(str.equals("bag")){
            if(this.playGameView.checkGamePanelVisiable()){
                this.playGameView.displayBagPanel();
            }else{
                this.playGameView.undisplayBagPanel();
            }
        }else if(str.equals("information")){
            if(this.playGameView.checkGamePanelVisiable()){
                this.playGameView.displayPlayerInformation(this.newGame);
                this.playGameView.displayInformationPanel();
            }else{
                this.playGameView.undisplayInformationPanel();
            }
        }else if(str.equals("Back to game")){
            this.playGameView.undisplaySystemPanel();
        }else if(str.equals("Save game")){
            this.newGame.updatePlayerDB();
        }else if(str.equals("Load game")){
            
        }else if(str.equals("conversation_take")){
            
            if(this.newGame.getGameState().equals(GameState.Start)){
                    this.newGame.setState(GameState.ChapterOne);
                }else if(this.newGame.getGameState().equals(GameState.ChapterOne)){
                    if(!this.newGame.getBossA().getAlive()){
                        this.newGame.setState(GameState.ChapterTwo);
                    }else{
                    }
                }else if(this.newGame.getGameState().equals(GameState.ChapterTwo)){
                    if(!this.newGame.getBossB().getAlive()){
                        this.newGame.setState(GameState.ChapterThree);
                    }else{
                    }
                }else if(this.newGame.getGameState().equals(GameState.ChapterThree)){
                    if(!this.newGame.getBossC().getAlive()){
                        this.newGame.setState(GameState.Win);
                    }else{
                    }
                }
            this.playGameView.undisplayConversationPanel();
        }else if(str.equals("conversation_cancel")){
            this.playGameView.undisplayConversationPanel();
        }
        
    }
              

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
        Position pPlayer = this.newGame.getPlayer().getPosition();
        
        if(e.getKeyCode()== KeyEvent.VK_UP)
        {
            this.newGame.movePlayer(Direction.NORTH);
            this.direction = Direction.NORTH;
            this.playGameView.update();
        }
        else if(e.getKeyCode() == KeyEvent.VK_DOWN)
        {
            this.newGame.movePlayer(Direction.SOUTH);
            this.direction = Direction.SOUTH;
            this.playGameView.update();
        }
        else if(e.getKeyCode() == KeyEvent.VK_LEFT)
        {
            this.newGame.movePlayer(Direction.WEST);
            this.direction = Direction.WEST;
            this.playGameView.update();
        }
        else if(e.getKeyCode() == KeyEvent.VK_RIGHT)
        {
            this.newGame.movePlayer(Direction.EAST);
            this.direction = Direction.EAST;
            this.playGameView.update();
        }
        else if(e.getKeyCode() == 32){
            Position pXman = this.newGame.getXman().getPosition();
            
            Position p1 = new Position(this.newGame.getWorld(), pXman.getRow() - 1, pXman.getColumn());
            Position p2 = new Position(this.newGame.getWorld(), pXman.getRow() + 1, pXman.getColumn());
            Position p3 = new Position(this.newGame.getWorld(), pXman.getRow(), pXman.getColumn() - 1);
            Position p4 = new Position(this.newGame.getWorld(), pXman.getRow(), pXman.getColumn() + 1);
            String str = "";
            
            if(pPlayer.equals(p1) || pPlayer.equals(p2) || pPlayer.equals(p3) || pPlayer.equals(p4) || pPlayer.equals(pXman)){
                if(this.newGame.getGameState().equals(GameState.Start)){
                    str = "The game has started. Go and kill the first boss!";
                }else if(this.newGame.getGameState().equals(GameState.ChapterOne)){
                    if(!this.newGame.getBossA().getAlive()){
                        str = "You have killed the first boss. Go and kill the second boss!";
                    }else{
                        str = "You have to kill the first boss!";
                    }
                }else if(this.newGame.getGameState().equals(GameState.ChapterTwo)){
                    if(!this.newGame.getBossB().getAlive()){
                        str = "You have killed the second boss. Go and kill the last boss!";
                    }else{
                        str = "You have to kill the second boss!";
                    }
                }else if(this.newGame.getGameState().equals(GameState.ChapterThree)){
                    if(!this.newGame.getBossC().getAlive()){
                        str = "You have won the game!";
                    }else{
                        str = "You have to kill the biggest boss!";
                    }
                }
                this.playGameView.displayConversationPanel(str);
            }
            
            if(this.newGame.getWorld().getSquare(pPlayer) instanceof Portal){
                if(this.newGame.getGameState().equals(GameState.ChapterOne) && this.newGame.getWorld().getFileName().equals("town.txt")){
                    this.newGame.changeMap("area_x.txt");
                }else if(this.newGame.getGameState().equals(GameState.ChapterTwo) && this.newGame.getWorld().getFileName().equals("town.txt")){
                    this.newGame.changeMap("area_y.txt");
                }else if(this.newGame.getGameState().equals(GameState.ChapterThree) && this.newGame.getWorld().getFileName().equals("town.txt")){
                    this.newGame.changeMap("area_z.txt");
                }else if(!this.newGame.getWorld().getFileName().equals("town.txt")){
                    this.newGame.changeMap("town.txt");
                }
                
                this.playGameView.update();
            }
           
        }
        
        
        this.update();
    }
    
    public void update(){
        
        this.playGameView.update();
        
        // map chang
        Position playerPosition = this.newGame.getPlayer().getPosition();
        if(this.newGame.getWorld().getMapSquare()[playerPosition.getRow()][playerPosition.getColumn()] instanceof Portal && 
                this.newGame.getGameState().equals(GameState.ChapterOne) && this.newGame.getWorld().getFileName().equals("town.txt")){
            this.newGame.changeMap("area_x.txt");
            this.playGameView.update();
        }else if(this.newGame.getWorld().getMapSquare()[playerPosition.getRow()][playerPosition.getColumn()] instanceof Portal && 
                this.newGame.getGameState().equals(GameState.ChapterTwo) && this.newGame.getWorld().getFileName().equals("town.txt")){
            this.newGame.changeMap("area_y.txt");
            this.playGameView.update();
        }else if(this.newGame.getWorld().getMapSquare()[playerPosition.getRow()][playerPosition.getColumn()] instanceof Portal && 
                this.newGame.getGameState().equals(GameState.ChapterThree) && this.newGame.getWorld().getFileName().equals("town.txt")){
            this.newGame.changeMap("area_z.txt");
            this.playGameView.update();
        }else if(this.newGame.getWorld().getMapSquare()[playerPosition.getRow()][playerPosition.getColumn()] instanceof Portal && 
                this.newGame.getGameState().equals(GameState.ChapterOne) && this.newGame.getWorld().getFileName().equals("area_x.txt")){
            this.newGame.changeMap("town.txt");
            this.playGameView.update();
        }else if(this.newGame.getWorld().getMapSquare()[playerPosition.getRow()][playerPosition.getColumn()] instanceof Portal && 
                this.newGame.getGameState().equals(GameState.ChapterTwo) && this.newGame.getWorld().getFileName().equals("area_y.txt")){
            this.newGame.changeMap("town.txt");
            this.playGameView.update();
        }else if(this.newGame.getWorld().getMapSquare()[playerPosition.getRow()][playerPosition.getColumn()] instanceof Portal && 
                this.newGame.getGameState().equals(GameState.ChapterThree) && this.newGame.getWorld().getFileName().equals("area_z.txt")){
            this.newGame.changeMap("town.txt");
            this.playGameView.update();
        }
        
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
//        Position pPlayer = this.newGame.getPlayer().getPosition();
//        if(e.getKeyCode() == 32){
//            if(!this.newGame.isMonsterWithPlayer().equals(null)){
//                if(!this.newGame.getMonsterOnPosition(pPlayer).equals(null)){
//                    this.playGameView.undisplay();
//                    FightControler fightControler = new FightControler(new Fight(this.playGameView.getFrame()), this.newGame);
//                }else if(!this.newGame.getBossOnPosition(pPlayer).equals(null)){
//                    this.playGameView.undisplay();
//                    FightControler fightControler = new FightControler(new Fight(this.playGameView.getFrame()), this.newGame);
//                }
//            }
//        }
    }
    
    
}