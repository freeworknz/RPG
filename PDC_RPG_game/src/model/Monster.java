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
public class Monster extends Role{
    
    private boolean alive;
            
    public Monster(){
        super();
        this.alive = true;
    }
    
    public Monster(MonsterAttributes monsterAttributes){
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
        this.addSkill(monsterAttributes.getSkill());
        this.alive = true;
    }
    
    public MonsterAttributes getMonsterAttributes(){
        for(MonsterAttributes m : MonsterAttributes.values()){
            if(this.getName().equals(m.getName())){
                return m;
            }
        }
        return null;
    }
    
    public boolean getAlive(){
        return this.alive;
    }
    
    public void setalive(boolean alive){
        this.alive = alive;
    }

    @Override
    public String getStringRepresentation() {
        return "Z";
    }
}
