//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIToolkitProfileService extends nsISupports
{
    public static final String NS_ITOOLKITPROFILESERVICE_IID = "{9b434f48-438c-4f85-89de-b7f321a45341}";
    
    boolean getStartWithLastProfile();
    
    void setStartWithLastProfile(final boolean p0);
    
    boolean getStartOffline();
    
    void setStartOffline(final boolean p0);
    
    nsISimpleEnumerator getProfiles();
    
    nsIToolkitProfile getSelectedProfile();
    
    void setSelectedProfile(final nsIToolkitProfile p0);
    
    nsIToolkitProfile getProfileByName(final String p0);
    
    nsIProfileLock lockProfilePath(final nsILocalFile p0, final nsILocalFile p1);
    
    nsIToolkitProfile createProfile(final nsILocalFile p0, final nsILocalFile p1, final String p2);
    
    long getProfileCount();
    
    void flush();
}
