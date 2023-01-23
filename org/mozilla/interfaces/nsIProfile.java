//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIProfile extends nsISupports
{
    public static final String NS_IPROFILE_IID = "{02b0625a-e7f3-11d2-9f5a-006008a6efe9}";
    public static final long SHUTDOWN_PERSIST = 1L;
    public static final long SHUTDOWN_CLEANSE = 2L;
    
    int getProfileCount();
    
    String[] getProfileList(final long[] p0);
    
    boolean profileExists(final String p0);
    
    String getCurrentProfile();
    
    void setCurrentProfile(final String p0);
    
    void shutDownCurrentProfile(final long p0);
    
    void createNewProfile(final String p0, final String p1, final String p2, final boolean p3);
    
    void renameProfile(final String p0, final String p1);
    
    void deleteProfile(final String p0, final boolean p1);
    
    void cloneProfile(final String p0);
}
