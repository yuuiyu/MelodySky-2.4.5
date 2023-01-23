//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsISessionRoaming extends nsISupports
{
    public static final String NS_ISESSIONROAMING_IID = "{ab62465c-494c-446e-b671-930bb98a7bc4}";
    
    void beginSession();
    
    void endSession();
    
    boolean isRoaming();
}
