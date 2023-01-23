//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.newdawn.slick;

public interface Font
{
    int getWidth(final String p0);
    
    int getHeight(final String p0);
    
    int getLineHeight();
    
    void drawString(final float p0, final float p1, final String p2);
    
    void drawString(final float p0, final float p1, final String p2, final Color p3);
    
    void drawString(final float p0, final float p1, final String p2, final Color p3, final int p4, final int p5);
}
