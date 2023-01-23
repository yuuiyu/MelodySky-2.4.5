//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIResumableChannel extends nsISupports
{
    public static final String NS_IRESUMABLECHANNEL_IID = "{4ad136fa-83af-4a22-a76e-503642c0f4a8}";
    
    void resumeAt(final double p0, final String p1);
    
    String getEntityID();
}
