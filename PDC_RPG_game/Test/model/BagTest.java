/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author wyt4877
 */
public class BagTest {
    ArrayList<Medicine> hpMedicineList;
    ArrayList<Medicine> mpMedicineList;
    private Bag bag;
    public BagTest() {
    }

    @Before
    public void setUp() {
        bag= new Bag();
        hpMedicineList = new ArrayList<Medicine>();
       mpMedicineList=new ArrayList<Medicine>();

    }

    @After
    public void tearDown() {
        bag=null;
        hpMedicineList = null;
       mpMedicineList=null;
    }
     @Test
    public void testSetMedicine(){
        bag.setMedicine(Medicine.manaPotion);
         bag.setMedicine(Medicine.healthPotion);
         assertEquals(Medicine.healthPotion,bag.getHealthMedicine().get(0));
         assertEquals(Medicine.manaPotion,bag.getManaMedicine().get(0));
    }
}
