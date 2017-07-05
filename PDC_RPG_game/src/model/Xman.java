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
public class Xman extends Occupant {

    private String name;
    private Medicine HpMedicine;
    private Medicine MpMedicine;
    private Armor[] armor = new Armor[2];
    private Weapon[] weapon = new Weapon[2];

    public Xman(Position p) {
        this.setPosition(p);
        this.name = "Xman";
        this.HpMedicine = Medicine.healthPotion;
        this.MpMedicine = Medicine.manaPotion;
        weapon[0] = Weapon.BFSword;
        weapon[1] = Weapon.theBloodthirster;
        armor[0] = Armor.chainVest;
        armor[1] = Armor.frozenHeart;
    }

    public Weapon getWeapon(int i) {
        return weapon[i];

    }

    public Armor getArmor(int i) {
        return armor[i];
    }

    public Medicine getHpMedicine() {
        return HpMedicine;
    }

    public Medicine getMpMedicine() {
        return MpMedicine;
    }

    public int getCost() {
        return HpMedicine.getCost();
    }

    @Override
    public String getStringRepresentation() {
        return "X";
    }
}
