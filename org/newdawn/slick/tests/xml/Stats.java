//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.newdawn.slick.tests.xml;

public class Stats
{
    private int hp;
    private int mp;
    private float age;
    private int exp;
    
    public void dump(final String prefix) {
        System.out.println(prefix + "Stats " + this.hp + "," + this.mp + "," + this.age + "," + this.exp);
    }
}
