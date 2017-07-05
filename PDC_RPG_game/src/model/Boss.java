/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author wyt4877
 */
public class Boss extends Monster {

    //ArrayList<Skill> skillList = new ArrayList<Skill>();

    public Boss() {
        super();
        //this.setSkill(skillList);

    }
    
    public Boss(MonsterAttributes monsterAttributes) {
        super();
        this.setAtk(monsterAttributes.getAtk());
        this.setDef(monsterAttributes.getDef());
        this.setExp(monsterAttributes.getExp());
        this.setHp(monsterAttributes.getHp());
        this.setLv(monsterAttributes.getLv());
        this.setMaxHp(monsterAttributes.getMaxHp());
        this.setMaxMp(monsterAttributes.getMaxMp());
        this.setMoney(monsterAttributes.getMoney());
        this.setMp(monsterAttributes.getMp());
        this.setName(monsterAttributes.getName());
        this.setBossSkill();

    }

    public void setBossSkill() {
        Skill skill = null;
        if (this.getName().equals("bossA")) {
            skill = Skill.bossASkill;
        } else if (this.getName().equals("bossB")) {
            skill = Skill.bossBSkill;
        } else if (this.getName().equals("bossC")) {
            skill = Skill.bossCSkill;
        } else {
            skill = null;
        }
        this.addSkill(skill);
    }

    public int bossAttack() {
        int realAtk=0;
        Random random = new Random();
        int ran = random.nextInt(100);
        if (ran >= 30) {
            realAtk = this.attack(null);
        } else {
            realAtk = this.useSkill(this.getSkill().get(0));
        }
        return realAtk;
    }
    
    @Override
    public String getStringRepresentation() {
        return "O";
    }

}
