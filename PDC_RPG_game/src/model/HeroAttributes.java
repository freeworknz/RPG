/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

/**
 *
 * @author york
 */
public enum HeroAttributes {

    saber("saber", 100, 100, 100, 100, 8,  2, 1, 0, 0, null, null),
    caster("caster", 80, 80, 120, 120, 5,  1, 1, 0, 0, null, null),
    lancer("lancer", 120, 120, 80, 80, 10,  3, 1, 0, 0,  null, null);
    //Character attributes
    private String name;
    private int maxHp;
    private int hp;
    private int maxMp;
    private int mp;
    private int Atk;
    private int def;
    private int lv;
    private int exp;
    private int money;
    private Skill [] skill;
    private Weapon weapon;

    private HeroAttributes(String name, int maxHp, int hp, int maxMp, int mp, int Atk,  int def, int lv, int exp, int money, Skill []skill,Weapon weapon) {
        this.name = name;
        this.maxHp = maxHp;
        this.hp = hp;
        this.maxMp = maxMp;
        this.mp = mp;
        this.Atk = Atk;
        this.def = def;
        this.lv = lv;
        this.exp = exp;
        this.money = money;
        this.skill = skill;
        this.weapon = weapon;
    }

    //get 
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
        return lv;
    }

    public int getExp() {
        return exp;
    }

    public int getMoney() {
        return money;
    }

    public Skill[] getSkill() {
        return skill;
    }

   
    public Weapon getWeapon() {
        return weapon;
    }

}
