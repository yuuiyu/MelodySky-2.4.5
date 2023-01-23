//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIScriptSecurityManager extends nsIXPCSecurityManager
{
    public static final String NS_ISCRIPTSECURITYMANAGER_IID = "{f4d74511-2b2d-4a14-a3e4-a392ac5ac3ff}";
    public static final long STANDARD = 0L;
    public static final long DISALLOW_FROM_MAIL = 1L;
    public static final long ALLOW_CHROME = 2L;
    public static final long DISALLOW_SCRIPT_OR_DATA = 4L;
    public static final long DISALLOW_SCRIPT = 8L;
    
    void checkLoadURI(final nsIURI p0, final nsIURI p1, final long p2);
    
    void checkLoadURIStr(final String p0, final String p1, final long p2);
    
    boolean isCapabilityEnabled(final String p0);
    
    void enableCapability(final String p0);
    
    void revertCapability(final String p0);
    
    void disableCapability(final String p0);
    
    void setCanEnableCapability(final String p0, final String p1, final short p2);
    
    void checkSameOriginURI(final nsIURI p0, final nsIURI p1);
}
