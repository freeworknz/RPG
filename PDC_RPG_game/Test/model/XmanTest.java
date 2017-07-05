/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author wyt4877
 */
public class XmanTest {

    private Xman xman;
    private Position p = new Position(new Map("town.txt"), 1, 1);

    public XmanTest() {
    }

    @Before
    public void setUp() {
        
        xman = new Xman(p);

    }

    @After
    public void tearDown() {
        xman = null;

    }

    @Test
    public void testGetWeapon() {
        assertEquals(Weapon.BFSword, xman.getWeapon(0));
        assertEquals(Weapon.theBloodthirster, xman.getWeapon(1));
    }

    @Test
    public void testGetArmor() {
        assertEquals(Armor.chainVest, xman.getArmor(0));
        assertEquals(Armor.frozenHeart, xman.getArmor(1));
    }

    @Test
    public void testGetHpMedicine() {
        assertEquals(Medicine.healthPotion, xman.getHpMedicine());

    }

    @Test
    public void testGetMpMedicine() {
        assertEquals(Medicine.manaPotion, xman.getMpMedicine());

    }
}
