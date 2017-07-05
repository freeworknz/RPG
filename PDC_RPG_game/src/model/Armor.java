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
public enum Armor {

    clothAromr("clothAromr", 40, 750),
    chainVest("chainVest", 15, 300),
    frozenHeart("frozenHeart", 75, 1500),
    Thornmail("Thornmail", 100, 2100);
    private String name;
    private int def;
    private int cost;

    private Armor(String name, int def, int cost) {
        this.name = name;
        this.def = def;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public int getDef() {
        return def;

    }

    public int getCost() {
        return cost;
    }
}
