/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author york
 */
public class Bag {

    private ArrayList<Medicine> hpMedicineList;
    private ArrayList<Medicine> mpMedicineList;

    public Bag() {
        hpMedicineList = new ArrayList<Medicine>();
        mpMedicineList = new ArrayList<Medicine>();
    }

    public ArrayList<Medicine> getHealthMedicine() {

        return hpMedicineList;

    }

    public ArrayList<Medicine> getManaMedicine() {

        return mpMedicineList;

    }

    public void setMedicine(Medicine med) {
        if (med.getName().equals("healthPotion") && hpMedicineList.size() < 10) {
            hpMedicineList.add(med);
        }
        if (med.getName().equals("manaPotion") && mpMedicineList.size() < 10) {
            mpMedicineList.add(med);
        }
    }
    
    public void removeMedicine(Medicine med) {
        if (med.getName().equals("healthPotion") && hpMedicineList.size() > 0) {
            hpMedicineList.remove(med);
        }
        if (med.getName().equals("manaPotion") && mpMedicineList.size() > 0) {
            mpMedicineList.remove(med);
        }
    }
    
    
}
