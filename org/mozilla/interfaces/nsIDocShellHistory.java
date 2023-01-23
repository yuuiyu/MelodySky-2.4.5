//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDocShellHistory extends nsISupports
{
    public static final String NS_IDOCSHELLHISTORY_IID = "{89caa9f0-8b1c-47fb-b0d3-f0aef0bff749}";
    
    nsISHEntry getChildSHEntry(final int p0);
    
    void addChildSHEntry(final nsISHEntry p0, final nsISHEntry p1, final int p2);
    
    boolean getUseGlobalHistory();
    
    void setUseGlobalHistory(final boolean p0);
}
