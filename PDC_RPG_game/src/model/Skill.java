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
public enum Skill {

    saberSkill1("skill1Name", 50, 50, 2),
    saberSkill2("skill2Name", 100, 100, 5),
    saberSkill3("skill3Name", 200, 200, 8),
    lancerSkill1("skill1Name", 50, 50, 2),
    lancerSkill2("skill2Name", 100, 100, 5),
    lancerSkill3("skill3Name", 200, 200, 8),
    casterSkill1("skill1Name", 50, 50, 2),
    casterSkill2("skill2Name", 100, 100, 5),
    casterSkill3("skill3Name", 200, 200, 8),
    bossASkill("bossASkill", 100, 100, 0),
    bossBSkill("bossBSkill", 200, 200, 0),
    bossCSkill("bossCSkill", 300, 300, 0);

    private String skillName;
    private int skillAtk;
    private int skillMpCost;
    private int skillLevelLock;

    private Skill(String skillName, int skillAtk, int skillMpCost, int skillLevelLock) {
        this.skillName = skillName;
        this.skillAtk = skillAtk;
        this.skillMpCost = skillMpCost;
        this.skillLevelLock = skillLevelLock;
    }

    public String getSkillName() {
        return skillName;
    }

    public int getSkillAtk() {
        return skillAtk;
    }

    public int getSkillMpCost() {
        return skillMpCost;
    }

    public int getSkillLevelLock() {
        return skillLevelLock;
    }
}
