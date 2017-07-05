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
 * @author york
 */
public abstract class Role extends Occupant{
//name of role
    private String name;
    //Maximum Health
    private int maxHp;
    //Current Health
    private int hp;
    //Maximum Mana
    private int maxMp;
    //Current Mana
    private int mp;
    //Attack
    private int Atk;
    //Defense
    private int def;
    //level
    private int level;
    //Experience
    private int exp;
    //money
    private int money;
    //skill list of role (null when role do not have it)
    private ArrayList<Skill> skillList;
/**
 * Creates a new Role instance without a name maxHp hp 
 * maxMp mp Atk def level exp money skillList.
 * 
 */
    public Role() {
        this.name = null;
        this.maxHp = 0;
        this.hp = 0;
        this.maxMp = 0;
        this.mp = 0;
        this.Atk = 0;
        this.def = 0;
        this.level = 0;
        this.exp = 0;
        this.money = 0;
        this.skillList=new ArrayList<Skill>();
    }
/**
 * attack of the role
 * @param weapon
 * @return role's attack(when role don not have a weapon, weapon is null )
 */
    public int attack(Weapon weapon) {
        int atk = 0;
        if (weapon == null) {
            atk = this.getAtk();
        } else {
            atk = this.getAtk() + weapon.getAtk();
        }
        int realAtk = 0;
        Random random = new Random();
        int rand = random.nextInt(100);

        if (rand >= 4) {
            if (rand <=90) {
                realAtk = atk + (int) (Math.random() * (((int) atk * 1.5 - atk) + 1));
            } else {
                realAtk = atk * 2;
            }
        }
        return realAtk;
    }

/**
 * role have enough mana to use the skill or not
 * @param skill a checked skill
 * @return true if it can use
 */
    public boolean isMpEnough(Skill skill) {
        return this.getMp() >= skill.getSkillMpCost();
    }
/**
 * skill is locked or not
 * @param skill a checked skill
 * @return true if not locked
 */
    public boolean isSkillAva(Skill skill) {
        for (Skill ckeckSkill : skillList) {
            if (ckeckSkill.equals(skill)) {
                return true;
            }
        }
        return false;
    }
/**
 * use skill 
 * @param skill a used skill
 * @return skill demage 
 */
    public int useSkill(Skill skill) {
        int skillAtk = 0;
        if (this.isMpEnough(skill) && isSkillAva(skill)) {
            skillAtk = skill.getSkillAtk();
            this.setMp(this.getMp() - skill.getSkillMpCost());
        }
        
        return skillAtk;
    }

    

    public String getName() {
        return name;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public int getHp() {
        return hp;
    }

    public int getMaxMp() {
        return maxMp;
    }

    public int getMp() {
        return mp;
    }

    public int getAtk() {
        return Atk;
    }

    public int getDef() {
        return def;
    }

    public int getLv() {
        return level;
    }

    public int getExp() {
        return exp;
    }

    public int getMoney() {
        return money;
    }

    public ArrayList<Skill> getSkill() {
        return skillList;
    }
//set 

    public void setName(String name) {
        this.name = name;
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setMaxMp(int maxMp) {
        this.maxMp = maxMp;
    }

    public void setMp(int mp) {
        this.mp = mp;
    }

    public void setAtk(int Atk) {
        this.Atk = Atk;
    }

    public void setDef(int def) {
        this.def = def;
    }

    public void setLv(int lv) {
        this.level = lv;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void setSkill(ArrayList<Skill> skillList) {
        this.skillList=skillList;
    }
    
    public void addSkill(Skill skill) {
        this.skillList.add(skill);
    }
}
