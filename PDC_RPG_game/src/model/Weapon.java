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
public enum Weapon {

    longSword("longSword", 10, 360),
    BFSword("BFSword", 50, 800),
    theBloodthirster("theBloodthirster", 100, 1500),
    doransBlade("doransBlade", 200, 2400);
    private String name;
    private int atk;
    private int cost;

    private Weapon(String name, int atk, int cost) {
        this.name = name;
        this.atk = atk;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public int getAtk() {
        return atk;

    }

    public int getCost() {
        return cost;
    }
}
