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
public enum MonsterAttributes {

    MONSTERA("monsterA", 50, 50, 0, 0, 12, 0, 2, 2, 55, null),
    BOSSA("bossA", 500, 500, 300, 300, 40, 30, 4, 10, 1000, null),
    MONSTERB("monsterB", 150, 150, 20, 20, 25, 15, 3, 4, 120, null),
    BOSSB("bossB", 700, 700, 1000, 1000, 30, 20, 7, 20, 3000, null),
    BOSSC("bossC", 1000, 1000, 1000, 1000, 100, 100, 9, 10, 0, null);

    private String name;
    private int maxHp;
    private int hp;
    private int maxMp;
    private int mp;
    private int atk;
    private int def;
    private int lv;
    private int exp;
    private int money;
    private Skill skill;

    private MonsterAttributes(String name, int maxHp, int hp, int maxMp, int mp, int atk, int def, int lv, int exp, int money, Skill skill) {
        this.name = name;
        this.maxHp = maxHp;
        this.hp = hp;
        this.maxMp = maxMp;
        this.mp = mp;
        this.atk = atk;
        this.def = def;
        this.lv = lv;
        this.exp = exp;
        this.money = money;
        this.skill = skill;
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
        return atk;
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
    
    public Skill getSkill(){
        return this.skill;
    }

}
