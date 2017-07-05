/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import static controlers.NewGameControler.url;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import model.Armor;
import model.Boss;
import model.HeroAttributes;
import model.Map;
import model.MapSquare;
import model.Medicine;
import model.Monster;
import model.MonsterAttributes;
import model.Player;
import model.Position;
import model.Skill;
import model.Weapon;
import model.Xman;

/**
 *
 * @author Robert
 */
public class Game extends Observable{
    
    static int monsterNo = 10;
    final static String mapMain = "town.txt";
    final static String mapX = "area_x.txt";
    final static String mapY = "area_y.txt";
    final static String mapZ = "area_z.txt";
    
    private Map world;
    private Player player;
    private Boss bossA;
    private Boss bossB;
    private Boss bossC;
    private GameState state;
    private Xman xMan;
    private List<Monster> monsters; 
    
    public Game(){
        this.world = new Map(mapMain);
        this.player = new Player();
        this.bossA = new Boss(MonsterAttributes.BOSSA);
        this.bossB = new Boss(MonsterAttributes.BOSSB);
        this.bossC = new Boss(MonsterAttributes.BOSSC);
        this.state = GameState.NotStart;
        this.monsters = new ArrayList<Monster>();
    }
    
    public Game(Map world, Player player, GameState gameState){
        this.world = world;
        this.player = player;
        this.state = gameState;
        this.bossA = new Boss(MonsterAttributes.BOSSA);
        this.bossB = new Boss(MonsterAttributes.BOSSB);
        this.bossC = new Boss(MonsterAttributes.BOSSC);
    }
    
    public Map getWorld(){
        return this.world;
    }
    
    public GameState getGameState(){
        return this.state;
    }
    
    public Xman getXman(){
        return this.xMan;
    }
    
    public Monster getBossA(){
        return this.bossA;
    }
    
    public Monster getBossB(){
        return this.bossB;
    }
    
    public Monster getBossC(){
        return this.bossC;
    }
    
    public void setWorld(String map){
        this.world = new Map(map);
        notifyObservers(this.world);
    }
    
    public void setPlayerName(String playerName){
        this.player.setName(playerName);
        notifyObservers(this.player);
    }
    
    public void setState(GameState state){
        this.state = state;
        notifyObservers(this.state);
    }
    
    public void setXman(Xman xMan){
        this.xMan = xMan;
    }
    
    public void startGame(){
        
//        this.setWorld(mapMain);
        
        MapSquare destination = this.world.getEmptyRandomSquare();
        //this.player = new Player(playerName);
        destination.addOccupant(this.player);
        if(!this.getWorld().equals("town.txt") &&
                this.getGameState().equals(GameState.ChapterOne)){
            this.setupMonsters(new Monster(MonsterAttributes.MONSTERA));
        }else if(!this.getWorld().equals("town.txt") && this.getGameState().equals(GameState.ChapterTwo)){
            this.setupMonsters(new Monster(MonsterAttributes.MONSTERB));
        }
    }
    
    public void changeMap(String map){
        this.setWorld(map);
        if(map.equals(mapX)){
            Monster monster = new Monster(MonsterAttributes.MONSTERA);
            this.setupMonsters(monster);
        }else if(map.equals(mapY)){
            Monster monster = new Monster(MonsterAttributes.MONSTERB);
            this.setupMonsters(monster);
        }
        this.world.getEmptyRandomSquare().addOccupant(this.player);
    }
    
    private void setupMonsters(Monster monster)
    {
        this.monsters = new ArrayList<Monster>();
        for(int i = 0; i < 10; i++){
            MapSquare square = world.getEmptyRandomSquare();
            square.addOccupant(monster);
            this.monsters.add(monster);
        }
        notifyObservers(this.monsters);
    }
    
    private boolean gameInPlay(){
        return (state.equals(GameState.ChapterOne) || state.equals(GameState.ChapterTwo) || state.equals(GameState.ChapterThree)
                || state.equals(GameState.Start)) && (this.player != null); 
    }
    
    // Movement related
    
    private boolean isMovePossible(Direction direction){
        Position newPosition  =  this.world.calculateDestination(player.getPosition(), direction);
        return this.gameInPlay() && (newPosition != null);
    }
    
    public boolean movePlayer(Direction direction){
        boolean success = false;
        if ( this.isMovePossible(direction) )
        {
            // remove player from current square
            Position    from       = this.player.getPosition();
            MapSquare fromSquare = this.world.getSquare(from);
            fromSquare.removeOccupant(this.player);
            
            // calculate destination
            Position    to       = this.world.calculateDestination(from, direction);
            MapSquare toSquare = this.world.getSquare(to);
            // add player to the destination square
            toSquare.addOccupant(player);
            success = true;
        }
        return success;
    }
    
    // Obtain elements
    
    public void playerGainWeapon(){
        if(this.state.equals(GameState.ChapterOne)){
            player.setWeapon(Weapon.longSword);
        }else if(this.state.equals(GameState.ChapterTwo)){
            player.setWeapon(Weapon.BFSword);
        }else if(this.state.equals(GameState.ChapterThree)){
            player.setWeapon(Weapon.theBloodthirster);
        }
        notifyObservers(this.player);
    }
    
    public void playerGainArmor(){
        if(this.state.equals(GameState.ChapterOne)){
            player.setArmor(Armor.clothAromr);
        }else if(this.state.equals(GameState.ChapterTwo)){
            player.setArmor(Armor.chainVest);
        }else if(this.state.equals(GameState.ChapterThree)){
            player.setArmor(Armor.frozenHeart);
        }
        notifyObservers(this.player);
    }
    
    public boolean buyMedicine(Medicine medicine){
        if(this.player.getMoney() > medicine.getCost()){
            if(medicine.equals(Medicine.healthPotion) && this.player.getBag().getHealthMedicine().size() < 10){
                this.player.costMoney(xMan);
                this.player.getBag().setMedicine(medicine);
                return true;
            }else if(medicine.equals(Medicine.manaPotion) && this.player.getBag().getManaMedicine().size() < 10){
                this.player.costMoney(xMan);
                this.player.getBag().setMedicine(medicine);
                return true;
            }
        }
        notifyObservers(this.player);
        return false;
    }
    
    // Fight
    
    public MapSquare isMonsterWithPlayer(){
        if(this.world.getMapSquare()[this.player.getPosition().getRow()][this.player.getPosition().getColumn()].getOccupantsAsArray().length == 2){
            return this.world.getMapSquare()[this.player.getPosition().getRow()][this.player.getPosition().getColumn()];
        }else{
            return null;
        }
    }
    
    public Monster getMonsterOnPosition(Position pos){
        Monster m = null;
        for(Monster monster: this.monsters){
            if(monster.getPosition().equals(pos)){
                m = monster;
            }
        }
        return m;
    }
    
    public Boss getBossOnPosition(Position pos){
        Boss boss = null;
        if(this.bossA.getPosition() == pos){
            boss = this.bossA;
        }else if(this.bossB.getPosition() == pos){
            boss = this.bossB;
        }else if(this.bossC.getPosition() == pos){
            boss = this.bossC;
        }
        return boss;
    }
    
    public boolean playerHitMonster(Skill skill){
        Position pos = this.player.getPosition();
        if(!this.isMonsterWithPlayer().equals(null)){
            if(this.getMonsterOnPosition(pos).getHp() > 0){
                if(skill.equals(null)){
                    this.getMonsterOnPosition(pos).setHp(this.getMonsterOnPosition(pos).getHp() - this.player.attack(this.player.getWeapon()));
                }else{
                    this.getMonsterOnPosition(pos).setHp(this.getMonsterOnPosition(pos).getHp() - this.player.useSkill(skill));
                }
                if(this.getMonsterOnPosition(pos).getHp() <= 0){
                this.world.getMapSquare()[pos.getRow()][pos.getColumn()].removeOccupant(this.getMonsterOnPosition(pos));
                return true;
                }
            }else{
                return true;
            }
            return false;
        }
        notifyObservers(this.getMonsterOnPosition(pos));
        return false;
    }
    
    public boolean monsterHitPlayer(Skill skill){
        Position pos = this.player.getPosition();
        if(!this.isMonsterWithPlayer().equals(null)){
            if(this.player.getHp() > 0){
                if(skill.equals(null) && !(this.getMonsterOnPosition(pos) instanceof Boss)){
                    this.player.setHp(this.player.getHp() + this.player.holdAromr(this.player.getArmor())- this.getMonsterOnPosition(pos).attack(null));
                }else if(this.getMonsterOnPosition(pos) instanceof Boss){
                    Boss boss = new Boss(this.getMonsterOnPosition(pos).getMonsterAttributes());
                    this.player.setHp(this.player.getHp() + this.player.holdAromr(this.player.getArmor())- boss.bossAttack());
                }
                if(this.player.getHp() <= 0){
                    this.state = GameState.Lose;
                    return true;
                }
            }else{
                return true;
            }
            return false;
        }
        notifyObservers(this.player);
        return false;
    }
    
    // JDBC
    
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
    
    public void addNewPlayerDB(HeroAttributes hero, String playerName){
        try{
            Connection conn = DriverManager.getConnection(url);
            Statement statement = conn.createStatement();
            statement.executeQuery("inster into Player values (" + (statement.getMaxRows() + 1) + playerName + hero.getHp() + hero.getMaxHp() + hero.getMp() + 
                    hero.getMaxMp() + hero.getAtk() + hero.getDef() + hero.getLv() + hero.getExp() + hero.getMoney() + 1 + 1 + null + ")");
            statement.close();
            conn.close();
        }catch(SQLException e){
            System.err.println(e);
        }
        notifyObservers(this.player);
    }
    
    public void updatePlayerDB(){
        try{
            Connection conn = DriverManager.getConnection(url);
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("select W_id from Weapon");
            int i = Integer.parseInt(rs.toString());
            
            PreparedStatement ps = conn.prepareStatement("update Player set (P_maxhp, P_hp, P_maxmp, P_mp, P_attack, P_define, P_level, P_experience, P_money, W_id) "
                    + "= (?, ?, ?, ?, ?, ?, ?, ?, ?, ?) where P_name = " + this.player.getName());
            ps.setInt(1, this.player.getMaxHp());
            ps.setInt(2, this.player.getHp());
            ps.setInt(3, this.player.getMaxMp());
            ps.setInt(4, this.player.getMp());
            ps.setInt(5, this.player.getAtk());
            ps.setInt(6, this.player.getDef());
            ps.setInt(7, this.player.getLv());
            ps.setInt(8, this.player.getExp());
            ps.setInt(9, this.player.getMoney());
            ps.setInt(10, i);
            ps.execute();
            
            PreparedStatement ps1 = conn.prepareStatement("update Game set (G_state, G_map) = (?, ?) where P_id = " + this.player.getName());
            ps1.setString(1, this.getGameState().toString());
            ps1.setString(2, this.getWorld().getFileName());
            ps1.execute();
            
            statement.close();
            conn.close();
        }catch(SQLException e){
            System.err.println(e);
        }
        notifyObservers(this.player);
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
    
    public Player getPlayer(){
        return this.player;
    }
    
    public String [] getPlayerInformation(){
        String [] strs = new String [22];
        strs[0] = "Player Name:  ";
        strs[1] = this.player.getName();
        strs[2] = "Player HP:  ";
        strs[3] = "" + this.player.getHp() + "/" + this.player.getMaxHp();
        strs[4] = "Player MP:  ";
        strs[5] = "" + this.player.getMp() + "/" + this.player.getMaxMp();
        strs[6] = "Player Attack:  ";
        strs[7] = "" + this.player.getAtk();
        if(this.player.getWeapon() != null){
            strs[7] += "(" + this.player.getWeapon().getAtk()+")";
        }
        strs[8] = "Player Define:  ";
        strs[9] = "" + this.player.getDef();
        if(this.player.getArmor() != null){
            strs[9] += "(" + this.player.getArmor().getDef()+ ")";
        }
        strs[10] = "Player Level:  ";
        strs[11] = "" + this.player.getLv();
        strs[12] = "Player Experience:  ";
        strs[13] = "" + this.player.getExp();
        strs[14] = "Player Money:  ";
        strs[15] = "" + this.player.getMoney();
        strs[16] = "Player Weapon:  ";
        if(this.player.getWeapon() == null){
            strs[17] = "";
        }else{
            strs[17] = this.player.getWeapon().getName();
        }
        strs[18] = "Player Skill:  ";
        if(this.player.getLv() < 2){
            strs[19] = "";
        }else if(this.player.getLv() >= 2 && this.player.getLv() < 5){
            strs[19] = this.player.getSkill().get(0).getSkillName();
        }else if(this.player.getLv() >= 5 && this.player.getLv() < 8){
            strs[19] = this.player.getSkill().get(0).getSkillName() + "," + this.player.getSkill().get(1).getSkillName();
        }else if(this.player.getLv() >= 8){
            strs[19] = this.player.getSkill().get(0).getSkillName() + "," + this.player.getSkill().get(1).getSkillName() + 
                    "," + this.player.getSkill().get(2).getSkillName();
        }
        strs[20] = "Game State";
        strs[21] = this.getGameState().toString();
        return strs;
    }
}
