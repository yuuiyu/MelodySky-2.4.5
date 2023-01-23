//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsICRLManager extends nsISupports
{
    public static final String NS_ICRLMANAGER_IID = "{486755db-627a-4678-a21b-f6a63bb9c56a}";
    public static final long TYPE_AUTOUPDATE_TIME_BASED = 1L;
    public static final long TYPE_AUTOUPDATE_FREQ_BASED = 2L;
    
    void importCrl(final byte[] p0, final long p1, final nsIURI p2, final long p3, final boolean p4, final String p5);
    
    boolean updateCRLFromURL(final String p0, final String p1);
    
    nsIArray getCrls();
    
    void deleteCrl(final long p0);
    
    void rescheduleCRLAutoUpdate();
    
    String computeNextAutoUpdateTime(final nsICRLInfo p0, final long p1, final double p2);
}
