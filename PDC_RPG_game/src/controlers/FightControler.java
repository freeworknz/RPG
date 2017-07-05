/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlers;

import model.ui.Fight;
import game.Game;
import game.GameState;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import model.Boss;
import model.Monster;
import model.MonsterAttributes;
import model.Player;
import model.Position;
import model.Skill;
import pdc_rpg_game.PalyGameView;

/**
 *
 * @author Robert
 */
public class FightControler implements java.awt.event.ActionListener{

    private Game newGame;
    private Fight fight;
    private Player player;
    private Monster monster;
    private Boss boss;
    
    public FightControler(Fight fight, Game newGame){
        this.fight = fight;
        this.newGame = newGame;
        this.player = this.newGame.getPlayer();
        Position pPlayer = this.player.getPosition();
        if(!this.newGame.isMonsterWithPlayer().equals(null)){
            if(!this.newGame.getMonsterOnPosition(pPlayer).equals(null)){
                this.monster = this.newGame.getMonsterOnPosition(pPlayer);
                this.fight.setHPMP(monster, player);
                if(this.monster.getMonsterAttributes().equals(MonsterAttributes.MONSTERA)){
                    this.fight.setImage(new ImageIcon("src/image/lancer.png"), new ImageIcon("src/image/lancer.png"));
                }else if(this.monster.getMonsterAttributes().equals(MonsterAttributes.MONSTERB)){
                    this.fight.setImage(new ImageIcon("src/image/lancer.png"), new ImageIcon("src/image/lancer.png"));
                }
            }else if(!this.newGame.getBossOnPosition(pPlayer).equals(null)){
                this.boss = this.newGame.getBossOnPosition(pPlayer);
                this.fight.setHPMP(boss, player);
                if(this.boss.getMonsterAttributes().equals(MonsterAttributes.BOSSA)){
                    this.fight.setImage(new ImageIcon("src/image/lancer.png"), new ImageIcon("src/image/lancer.png"));
                }else if(this.boss.getMonsterAttributes().equals(MonsterAttributes.BOSSB)){
                    this.fight.setImage(new ImageIcon("src/image/lancer.png"), new ImageIcon("src/image/lancer.png"));
                }else if(this.boss.getMonsterAttributes().equals(MonsterAttributes.BOSSC)){
                    this.fight.setImage(new ImageIcon("src/image/lancer.png"), new ImageIcon("src/image/lancer.png"));
                }
            }
        }
        
        
        
        int level = this.newGame.getPlayer().getLv();
        if(level < 2){
            this.fight.setSkillAviliable(false, false, false);
        }else if(level >= 2 && level < 5){
            this.fight.setSkillAviliable(true, false, false);
        }else if(level >= 5 && level < 8){
            this.fight.setSkillAviliable(true, true, false);
        }else if(level >= 8){
            this.fight.setSkillAviliable(true, true, true);
        }
        
        this.fight.setActionListener(this);
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "attack":
                this.newGame.playerHitMonster(null);
                break;
            case "skill1":
                this.newGame.playerHitMonster(Skill.lancerSkill1);
                break;
            case "skill2":
                this.newGame.playerHitMonster(Skill.lancerSkill2);
                break;
            case "skill3":
                this.newGame.playerHitMonster(Skill.lancerSkill3);
                break;
        }
        this.update();
    }
    
    public void update(){
        if(this.player.getHp() <= 0){
            this.newGame.setState(GameState.Lose);
        }else if(!this.monster.equals(null) && this.monster.getHp() <= 0){
            PlayGameControler palyGameControler = new PlayGameControler(new PalyGameView(this.fight.getFrame()), this.newGame);
        }
        
        if(!this.monster.equals(null)){
            this.newGame.monsterHitPlayer(null);
        }else if(!this.boss.equals(null)){
            if(this.boss.getMonsterAttributes().equals(MonsterAttributes.BOSSA)){
                this.newGame.monsterHitPlayer(Skill.bossASkill);
            }else if(this.boss.getMonsterAttributes().equals(MonsterAttributes.BOSSB)){
                this.newGame.monsterHitPlayer(Skill.bossBSkill);
            }else if(this.boss.getMonsterAttributes().equals(MonsterAttributes.BOSSC)){
                this.newGame.monsterHitPlayer(Skill.bossCSkill);
            }
        }
    }
    
    
}
