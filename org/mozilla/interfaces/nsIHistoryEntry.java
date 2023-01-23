//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIHistoryEntry extends nsISupports
{
    public static final String NS_IHISTORYENTRY_IID = "{a41661d4-1417-11d5-9882-00c04fa02f40}";
    
    nsIURI getURI();
    
    String getTitle();
    
    boolean getIsSubFrame();
}
