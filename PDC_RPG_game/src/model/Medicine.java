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
public enum Medicine {

    healthPotion("healthPotion", 100, 300),
    manaPotion("manaPotion", 100, 300);
    private String name;
    private int restoresNum;
    private int cost;

    private Medicine(String name, int restoresNum, int cost) {
        this.name = name;
        this.restoresNum = restoresNum;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public int getRestoresNum() {
        return restoresNum;

    }

    public int getCost() {
        return cost;
    }
}