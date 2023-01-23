//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIProfileInternal extends nsIProfile
{
    public static final String NS_IPROFILEINTERNAL_IID = "{2f977d42-5485-11d4-87e2-0010a4e75ef2}";
    public static final long LIST_ONLY_NEW = 1L;
    public static final long LIST_ONLY_OLD = 2L;
    public static final long LIST_ALL = 3L;
    public static final long LIST_FOR_IMPORT = 4L;
    
    int get4xProfileCount();
    
    String[] getProfileListX(final long p0, final long[] p1);
    
    void migrateProfileInfo();
    
    void migrateAllProfiles();
    
    void migrateProfile(final String p0);
    
    void remigrateProfile(final String p0);
    
    void forgetCurrentProfile();
    
    void createDefaultProfile();
    
    nsIFile getProfileDir(final String p0);
    
    String getProfilePath(final String p0);
    
    nsILocalFile getOriginalProfileDir(final String p0);
    
    long getProfileLastModTime(final String p0);
    
    boolean getAutomigrate();
    
    void setAutomigrate(final boolean p0);
    
    nsIFile getDefaultProfileParentDir();
    
    String getFirstProfile();
    
    boolean getStartWithLastUsedProfile();
    
    void setStartWithLastUsedProfile(final boolean p0);
    
    void createNewProfileWithLocales(final String p0, final String p1, final String p2, final String p3, final boolean p4);
    
    boolean isCurrentProfileAvailable();
}
