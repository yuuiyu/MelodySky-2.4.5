//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.newdawn.slick.command;

public class BasicCommand implements Command
{
    public static char[] var1;
    public static String var2;
    public static String var3;
    public static String var4;
    public static String var5;
    public static String var6;
    private String name;
    
    public BasicCommand(final String name) {
        this.name = name;
    }
    
    public String getName() {
        return this.name;
    }
    
    @Override
    public int hashCode() {
        return this.name.hashCode();
    }
    
    @Override
    public boolean equals(final Object other) {
        return other instanceof BasicCommand && ((BasicCommand)other).name.equals(this.name);
    }
    
    @Override
    public String toString() {
        return "[Command=" + this.name + "]";
    }
}
