//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIPref extends nsISupports
{
    public static final String NS_IPREF_IID = "{a22ad7b0-ca86-11d1-a9a4-00805f8a7ac4}";
    public static final int ePrefInvalid = 0;
    public static final int ePrefLocked = 1;
    public static final int ePrefUserset = 2;
    public static final int ePrefConfig = 4;
    public static final int ePrefRemote = 8;
    public static final int ePrefLilocal = 16;
    public static final int ePrefString = 32;
    public static final int ePrefInt = 64;
    public static final int ePrefBool = 128;
    public static final int ePrefValuetypeMask = 224;
    
    void readUserPrefs(final nsIFile p0);
    
    void resetPrefs();
    
    void resetUserPrefs();
    
    void savePrefFile(final nsIFile p0);
    
    nsIPrefBranch getBranch(final String p0);
    
    nsIPrefBranch getDefaultBranch(final String p0);
    
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
    
    boolean prefIsLocked(final String p0);
    
    void lockPref(final String p0);
    
    void unlockPref(final String p0);
    
    void resetBranch(final String p0);
    
    void deleteBranch(final String p0);
    
    String[] getChildList(final String p0, final long[] p1);
    
    void addObserver(final String p0, final nsIObserver p1, final boolean p2);
    
    void removeObserver(final String p0, final nsIObserver p1);
    
    String copyCharPref(final String p0);
    
    String copyDefaultCharPref(final String p0);
    
    boolean getDefaultBoolPref(final String p0);
    
    int getDefaultIntPref(final String p0);
    
    void setDefaultBoolPref(final String p0, final boolean p1);
    
    void setDefaultCharPref(final String p0, final String p1);
    
    void setDefaultIntPref(final String p0, final int p1);
    
    String copyUnicharPref(final String p0);
    
    String copyDefaultUnicharPref(final String p0);
    
    void setUnicharPref(final String p0, final String p1);
    
    void setDefaultUnicharPref(final String p0, final String p1);
    
    String getLocalizedUnicharPref(final String p0);
    
    String getDefaultLocalizedUnicharPref(final String p0);
    
    nsIFileSpec getFilePref(final String p0);
    
    void setFilePref(final String p0, final nsIFileSpec p1, final boolean p2);
    
    nsILocalFile getFileXPref(final String p0);
    
    void setFileXPref(final String p0, final nsILocalFile p1);
}
