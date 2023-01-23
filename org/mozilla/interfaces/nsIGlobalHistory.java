//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIGlobalHistory extends nsISupports
{
    public static final String NS_IGLOBALHISTORY_IID = "{9491c383-e3c4-11d2-bdbe-0050040a9b44}";
    
    void addPage(final String p0);
    
    boolean isVisited(final String p0);
}
