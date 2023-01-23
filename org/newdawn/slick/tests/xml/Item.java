//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.newdawn.slick.tests.xml;

public class Item
{
    protected String name;
    protected int condition;
    
    public void dump(final String prefix) {
        System.out.println(prefix + "Item " + this.name + "," + this.condition);
    }
}
