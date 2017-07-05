/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 * Class for the player
 *
 * @author york
 */
public class Player extends Role {
//weapon that player uesd 

    
    private Weapon weapon;
    //aromr that player used
    private Armor armor;
    //medicine that player used
   
    //bag that player used
    private Bag bag;
    private Position position;

    /**
     * Creates a new player.
     */
    public Player(){
        super();
    }
    
    public Player(String name) {
        super();
        this.setName(name);
        this.bag = new Bag();
        this.weapon = null;
        this.armor = null;
    }
    
    public Player(HeroAttributes heroAttribute, String name) {
        super();
        this.setName(name);
        this.setAtk(heroAttribute.getAtk());
        this.setDef(heroAttribute.getDef());
        this.setExp(heroAttribute.getExp());
        this.setHp(heroAttribute.getHp());
        this.setLv(heroAttribute.getLv());
        this.setMaxHp(heroAttribute.getMaxHp());
        this.setMaxMp(heroAttribute.getMaxMp());
        this.setMoney(heroAttribute.getMoney());
        this.setMp(heroAttribute.getMp());
        this.setWeapon(heroAttribute.getWeapon());
        this.setArmor(Armor.clothAromr);
        this.setSkill(null);
    }


    /**
     * get Experience from monster if Experience enough then level up
     *
     * @param monster Provid Experience
     */
    public void gainExp(Monster monster) {
        this.setExp(this.getExp() + monster.getExp());
        while (this.getExp() >= 10) {
            this.setExp(this.getExp() - 10);
            this.setLv(this.getLv() + 1);
            this.setMaxHp(this.getMaxHp() + 100);
            this.setHp(this.getMaxHp());
            this.setMaxMp(this.getMaxMp() + 100);
            this.setMp(this.getMaxMp());
            this.setAtk((int) (this.getAtk() * 1.5));
            this.setDef((int) (this.getDef() * 1.5));
            
            if (this.getLv() == 2) {
                if (this.getName().equals("saber")) {
                    this.addSkill(Skill.saberSkill1);
                } else if (this.getName().equals("lancer")) {
                    this.addSkill(Skill.lancerSkill1);
                } else if (this.getName().equals("caster")) {
                    this.addSkill(Skill.casterSkill1);
                }
            }
            if (this.getLv() == 5) {
                if (this.getName().equals("saber")) {
                    this.addSkill(Skill.saberSkill2);
                } else if (this.getName().equals("lancer")) {
                    this.addSkill(Skill.lancerSkill2);
                } else if (this.getName().equals("caster")) {
                    this.addSkill(Skill.casterSkill2);
                }
            }
            if (this.getLv() == 8) {
                if (this.getName().equals("saber")) {
                    this.addSkill(Skill.saberSkill3);
                } else if (this.getName().equals("lancer")) {
                    this.addSkill(Skill.lancerSkill3);
                } else if (this.getName().equals("caster")) {
                    this.addSkill(Skill.casterSkill3);
                }
            }
        }
    }

    /**
     * pick Up Money from monster
     *
     * @param monster Provid Money
     */
    public void pickUpMoney(Monster monster) {
        this.setMoney(this.getMoney() + monster.getMoney());
    }

//costmoney
    /**
     * hold aromr
     *
     * @param armor
     * @return player's Defense + armor's Defense
     */
    public int holdAromr(Armor armor) {
        int realDef = 0;
        return realDef = this.getDef() + armor.getDef();
    }

    /**
     * Recover health/mana
     *
     * @param medicine
     */
    public void useMedicine(Medicine medicine) {

        if (medicine.equals(Medicine.healthPotion)&&this.getBag().getHealthMedicine().size()>0) {
            if (this.getHp() < this.getMaxHp()) {
                this.setHp(this.getHp() + medicine.getRestoresNum());
                if (this.getHp() > this.getMaxHp()) {
                    this.setHp(this.getMaxHp());
                }
            }
            this.getBag().removeMedicine(medicine);
        } else if (medicine.equals(Medicine.manaPotion)&&this.getBag().getManaMedicine().size()>0) {
            if (this.getMp() < this.getMaxMp()) {
                this.setMp(this.getMp() + medicine.getRestoresNum());
                if (this.getMp() > this.getMaxMp()) {
                    this.setMp(this.getMaxMp());
                }
            }
            this.getBag().removeMedicine(medicine);
        }
    }

    public void costMoney(Xman xman) {
        if (this.getMoney() > xman.getCost()) {
            this.setMoney(this.getMoney() - xman.getCost());
        }
    }

    public int buyMedicine(Xman xman, Medicine med) {
        if (this.getMoney() > xman.getCost()) {
            this.costMoney(xman);
            this.getBag().setMedicine(med);
            return 1;
        }
        return 0;
    }

    public Bag getBag() {
        return bag;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public Armor getArmor() {
        return armor;
    }

//    public void setSkill(Skill skill) {
//        this.skillList.add(skill); 
//    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public void setArmor(Armor armor) {
        this.armor = armor;
    }
    
    public HeroAttributes getHeroAttribute(){
        for(HeroAttributes h : HeroAttributes.values()){
            if(h.getName().equals(this.getName())){
                return h;
            }
        }
        return null;
    }
    
//    public String [] getPlayerInformation(){
//        String [] strs = new String [20];
//        strs[0] = "Player Name:  ";
//        strs[1] = this.getName();
//        strs[2] = "Player HP:  ";
//        strs[3] = "" + this.getHp() + "/" + this.getMaxHp();
//        strs[4] = "Player MP:  ";
//        strs[5] = "" + this.getMp() + "/" + this.getMaxMp();
//        strs[6] = "Player Attack:  ";
//        strs[7] = "" + this.getAtk() +"(" + this.getWeapon().getAtk()+")";
//        strs[8] = "Player Define:  ";
//        strs[9] = "" + this.getDef() +"(" + this.getArmor().getDef()+ ")";
//        strs[10] = "Player Level:  ";
//        strs[11] = "" + this.getLv();
//        strs[12] = "Player Experience:  ";
//        strs[13] = "" + this.getExp();
//        strs[14] = "Player Money:  ";
//        strs[15] = "" + this.getMoney();
//        strs[16] = "Player Weapon:  ";
//        if(this.getWeapon() == null){
//            strs[17] = "";
//        }else{
//            strs[17] = this.getWeapon().getName();
//        }
//        strs[18] = "Player Skill:  ";
//        if(this.getLv() < 2){
//            strs[19] = "";
//        }else if(this.getLv() >= 2 && this.getLv() < 5){
//            strs[19] = this.getSkill().get(0).getSkillName();
//        }else if(this.getLv() >= 5 && this.getLv() < 8){
//            strs[19] = this.getSkill().get(0).getSkillName() + "," + this.getSkill().get(1).getSkillName();
//        }else if(this.getLv() >= 8){
//            strs[19] = this.getSkill().get(0).getSkillName() + "," + this.getSkill().get(1).getSkillName() + "," + this.getSkill().get(2).getSkillName();
//        }
//        strs[19] = "Player Name";
//        return strs;
//    }
    
    
    @Override
    public String getStringRepresentation() {
        return "H";
    }

}
