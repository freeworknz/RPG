/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author wyt4877
 */
public class BossTest {
     private Player player;
   
    private Boss boss;

    public BossTest() {
    }

    @Before
    public void setUp() {
        player = new Player();
        
        boss = new Boss();

    }

    @After
    public void tearDown() {
        player = null;
        
        boss = null;
    }
     @Test
    public void testSetBossASkill(){
         boss.setName("bossA");
        boss.setBossSkill();
        assertEquals(Skill.bossASkill,boss.getSkill().get(0));
        
    }
     @Test
    public void testSetBossBSkill(){
         boss.setName("bossB");
        boss.setBossSkill();
        assertEquals(Skill.bossBSkill,boss.getSkill().get(0));
        
    }
     @Test
    public void testSetBossCSkill(){
         boss.setName("bossC");
        boss.setBossSkill();
        assertEquals(Skill.bossCSkill,boss.getSkill().get(0));
         assertEquals(1,boss.getSkill().size());
    }
//    @Test
//    public void testBossAttack(){
//        boss.setAtk(40);
//         boss.setName("bossA");
//        boss.setBossSkill();
//        boss.setMp(200);
//        assertTrue(boss.isMpEnough(boss.skillList.get(0)));
//        assertTrue(boss.isSkillAva(boss.skillList.get(0)));
//        assertEquals(10,boss.bossAttack());
//    }
}
