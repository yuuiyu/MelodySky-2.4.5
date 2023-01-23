//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.newdawn.slick.tests.xml;

public class Entity
{
    private float x;
    private float y;
    private Inventory invent;
    private Stats stats;
    
    private void add(final Inventory inventory) {
        this.invent = inventory;
    }
    
    private void add(final Stats stats) {
        this.stats = stats;
    }
    
    public void dump(final String prefix) {
        System.out.println(prefix + "Entity " + this.x + "," + this.y);
        this.invent.dump(prefix + "\t");
        this.stats.dump(prefix + "\t");
    }
}
