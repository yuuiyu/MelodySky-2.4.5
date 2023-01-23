//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.newdawn.slick;

public interface ControlledInputReciever
{
    void setInput(final Input p0);
    
    boolean isAcceptingInput();
    
    void inputEnded();
    
    void inputStarted();
}
