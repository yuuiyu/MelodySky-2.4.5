//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsINativeAppSupport extends nsISupports
{
    public static final String NS_INATIVEAPPSUPPORT_IID = "{5fdf8480-1f98-11d4-8077-00600811a9c3}";
    
    boolean start();
    
    void enable();
    
    boolean stop();
    
    void quit();
    
    void onLastWindowClosing();
    
    void reOpen();
}
