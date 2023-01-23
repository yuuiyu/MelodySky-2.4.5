//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIPrefBranch extends nsISupports
{
    public static final String NS_IPREFBRANCH_IID = "{56c35506-f14b-11d3-99d3-ddbfac2ccf65}";
    public static final int PREF_INVALID = 0;
    public static final int PREF_STRING = 32;
    public static final int PREF_INT = 64;
    public static final int PREF_BOOL = 128;
    
    String getRoot();
    
    int getPrefType(final String p0);
    
    boolean getBoolPref(final String p0);
    
    void setBoolPref(final String p0, final int p1);
    
    String getCharPref(final String p0);
    
    void setCharPref(final String p0, final String p1);
    
    int getIntPref(final String p0);
    
    void setIntPref(final String p0, final int p1);
    
    nsISupports getComplexValue(final String p0, final String p1);
    
    void setComplexValue(final String p0, final String p1, final nsISupports p2);
    
    void clearUserPref(final String p0);
    
    void lockPref(final String p0);
    
    boolean prefHasUserValue(final String p0);
    
    boolean prefIsLocked(final String p0);
    
    void unlockPref(final String p0);
    
    void deleteBranch(final String p0);
    
    String[] getChildList(final String p0, final long[] p1);
    
    void resetBranch(final String p0);
}
