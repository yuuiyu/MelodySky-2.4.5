//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.module.FMLModules.Utils;

public class HealthData
{
    private String name;
    private int health;
    private int maxHealth;
    private boolean attackable;
    
    public HealthData(final String name, final int health, final int maxHP, final boolean attackable) {
        this.name = name;
        this.health = health;
        this.maxHealth = maxHP;
        this.attackable = attackable;
    }
    
    public int getHealth() {
        return this.health;
    }
    
    public int getMaxHealth() {
        return this.maxHealth;
    }
    
    public String getName() {
        return this.name;
    }
    
    public boolean isAttackable() {
        return this.attackable;
    }
}
