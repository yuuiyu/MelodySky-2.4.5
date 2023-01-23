//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDOMStorageEvent extends nsIDOMEvent
{
    public static final String NS_IDOMSTORAGEEVENT_IID = "{fc540c28-8edd-4b7a-9c30-8638289b7a7d}";
    
    String getDomain();
    
    void initStorageEvent(final String p0, final boolean p1, final boolean p2, final String p3);
    
    void initStorageEventNS(final String p0, final String p1, final boolean p2, final boolean p3, final String p4);
}
