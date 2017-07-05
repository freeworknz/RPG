/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author wyt4877
 */
public class PlayerTest {

    private Player player;
    private Monster monster;
    private Boss boss;

    public PlayerTest() {
    }

    @Before
    public void setUp() {
        player = new Player();
        monster = new Monster();
        boss = new Boss();

    }

    @After
    public void tearDown() {
        player = null;
        monster = null;
        boss = null;
    }

    

    //IsMpEnough() TEST
    @Test
    public void testPlayerIsMpEnough() {
        player.setMp(100);

        assertTrue(player.isMpEnough(Skill.saberSkill1));
    }

    @Test
    public void testPlayerIsMpEnoughNotEnough() {
        player.setMp(100);

        assertFalse(player.isMpEnough(Skill.saberSkill3));
    }

    @Test
    public void testMonsterIsMpEnough() {

        boss.setMp(100);

        assertTrue(boss.isMpEnough(Skill.bossASkill));
    }

    @Test
    public void testMonsterIsMpEnoughNotEnough() {

        boss.setMp(100);

        assertFalse(boss.isMpEnough(Skill.bossCSkill));
    }

    @Test
    public void testPlayerIsSkillAva() {

        player.setName("saber");
        monster.setExp(100);
        player.gainExp(monster);
        assertTrue(player.isSkillAva(Skill.saberSkill1));
        assertTrue(player.isSkillAva(Skill.saberSkill2));
        assertTrue(player.isSkillAva(Skill.saberSkill3));
    }

    @Test
    public void testPlayerIsSkillAvaNotAve() {

        player.setName("saber");
        monster.setExp(10);
        player.gainExp(monster);
        assertFalse(player.isSkillAva(Skill.saberSkill1));
        assertFalse(player.isSkillAva(Skill.saberSkill2));
        assertFalse(player.isSkillAva(Skill.saberSkill3));
    }

    @Test
    public void testBossIsSkillAvaAve() {

        boss.setName("bossA");
        boss.setBossSkill();
        assertTrue(boss.isSkillAva(Skill.bossASkill));
        
    }

    @Test
    public void testBossAIsSkillAva() {

        boss.setName("bossA");
        boss.setBossSkill();
        assertTrue(boss.isSkillAva(Skill.bossASkill));
        
    }
    
    @Test
    public void testBossCIsSkillAvaNotAve() {
        boss.setName("bossC");
        assertFalse(boss.isSkillAva(Skill.bossCSkill));
    }

    @Test
    public void testPlayerUseSkill() {
        player.setName("saber");
        player.setMp(100);
        monster.setExp(100);
        player.gainExp(monster);
        assertEquals(1000, player.getMp());
        assertEquals(50, player.useSkill(Skill.saberSkill1));
        assertEquals(950, player.getMp());
        assertEquals(100, player.useSkill(Skill.saberSkill2));
        assertEquals(850, player.getMp());
        assertEquals(200, player.useSkill(Skill.saberSkill3));
        assertEquals(650, player.getMp());
    }

    @Test
    public void testPlayerUseSkillNoEnoughMp() {
        player.setName("saber");
        player.setMp(100);

        monster.setExp(20);
        player.gainExp(monster);
        player.setMp(0);
        assertEquals(0, player.useSkill(Skill.saberSkill1));

    }

    @Test
    public void testPlayerUseSkillSkillNotAve() {
        player.setName("saber");
        player.setMp(100);
        assertEquals(0, player.useSkill(Skill.saberSkill1));

    }

   
//@Test//random没法测
//public void testAttack(){
//    player.setAtk(10);
//    Weapon weapon =null;
//    assertEquals(10,player.getAtk());
//    assertEquals(10,weapon.longSword.getAtk());
//   assertEquals(20,player.attack(weapon.longSword));//null
//   
//}

    @Test
    public void testLvUp() {
        player.setName("saber");
        player.setHp(50);
        player.setMaxHp(100);
        player.setMaxMp(100);
        player.setMp(50);
        player.setExp(0);
        player.setLv(0);
        player.setAtk(10);
        monster.setExp(10);
        player.gainExp(monster);
        assertEquals(0, player.getExp());
        assertEquals(1, player.getLv());
        assertEquals(200, player.getMaxHp());
        assertEquals(200, player.getMaxMp());
        assertEquals(200, player.getHp());
        assertEquals(200, player.getMp());
        assertEquals(15, player.getAtk());
        
        monster.setExp(20);
        player.gainExp(monster);
        assertNotNull(player.getSkill().get(0));
        assertEquals(3, player.getLv());
        assertEquals("skill1Name", player.getSkill().get(0).getSkillName());
    }

    @Test
    public void testPickUpMoney() {
        player.setMoney(21);
        monster.setMoney(21);
        player.pickUpMoney(monster);
        assertEquals(42, player.getMoney());
    }
    @Test
    public void testHoldAromr(){
        player.setDef(10);
        assertEquals(110,player.holdAromr(Armor.Thornmail));
    }
    
    @Test
    public void testUseMedicineMedicineInBag(){
        player.setMaxHp(200);
        player.setHp(50);
        player.setMaxMp(200);
        player.setMp(50);
        player.getBag().setMedicine(Medicine.healthPotion);
        player.getBag().setMedicine(Medicine.manaPotion);
        player.useMedicine(Medicine.healthPotion);
        assertEquals(150,player.getHp());
        player.useMedicine(Medicine.manaPotion);
        assertEquals(150,player.getMp());
    }
    @Test
    public void testUseMedicineNoMedicineInBag(){
        player.setMaxHp(200);
        player.setHp(50);
        player.setMaxMp(200);
        player.setMp(50);
        player.useMedicine(Medicine.healthPotion);
        assertEquals(50,player.getHp());
        player.useMedicine(Medicine.manaPotion);
        assertEquals(50,player.getMp());
    }
     @Test
    public void testUseMedicineWhenHpAndMpIsFull(){
        player.setMaxHp(200);
        player.setHp(200);
        player.setMaxMp(200);
        player.setMp(200);
        player.useMedicine(Medicine.healthPotion);
        assertEquals(200,player.getHp());
        player.useMedicine(Medicine.manaPotion);
        assertEquals(200,player.getMp());
    }
     @Test
    public void testUseMedicineWhenHpAndMpWillFull(){
        player.setMaxHp(200);
        player.setHp(150);
        player.setMaxMp(200);
        player.setMp(150);
        player.getBag().setMedicine(Medicine.healthPotion);
         player.getBag().setMedicine(Medicine.manaPotion);
        player.useMedicine(Medicine.healthPotion);
        assertEquals(200,player.getHp());
        player.useMedicine(Medicine.manaPotion);
        assertEquals(200,player.getMp());
    }        
}
