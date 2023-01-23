//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIStyleSheetService extends nsISupports
{
    public static final String NS_ISTYLESHEETSERVICE_IID = "{41d979dc-ea03-4235-86ff-1e3c090c5630}";
    public static final long AGENT_SHEET = 0L;
    public static final long USER_SHEET = 1L;
    
    void loadAndRegisterSheet(final nsIURI p0, final long p1);
    
    boolean sheetRegistered(final nsIURI p0, final long p1);
    
    void unregisterSheet(final nsIURI p0, final long p1);
}
