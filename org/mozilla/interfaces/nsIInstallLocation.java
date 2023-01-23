//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIInstallLocation extends nsISupports
{
    public static final String NS_IINSTALLLOCATION_IID = "{d3d4dada-c6eb-11d9-a68f-001124787b2e}";
    public static final long PRIORITY_APP_PROFILE = 0L;
    public static final long PRIORITY_APP_SYSTEM_USER = 10L;
    public static final long PRIORITY_XRE_SYSTEM_USER = 100L;
    public static final long PRIORITY_APP_SYSTEM_GLOBAL = 1000L;
    public static final long PRIORITY_XRE_SYSTEM_GLOBAL = 10000L;
    
    String getName();
    
    nsIDirectoryEnumerator getItemLocations();
    
    nsIFile getLocation();
    
    boolean getRestricted();
    
    boolean getCanAccess();
    
    int getPriority();
    
    nsIFile getItemLocation(final String p0);
    
    String getIDForLocation(final nsIFile p0);
    
    nsIFile getItemFile(final String p0, final String p1);
    
    boolean itemIsManagedIndependently(final String p0);
}
