//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsISound extends nsISupports
{
    public static final String NS_ISOUND_IID = "{b148eed1-236d-11d3-b35c-00a0cc3c1cde}";
    
    void play(final nsIURL p0);
    
    void playSystemSound(final String p0);
    
    void beep();
    
    void init();
}
