//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIPermissionManager extends nsISupports
{
    public static final String NS_IPERMISSIONMANAGER_IID = "{4f6b5e00-0c36-11d5-a535-0010a401eb10}";
    public static final long UNKNOWN_ACTION = 0L;
    public static final long ALLOW_ACTION = 1L;
    public static final long DENY_ACTION = 2L;
    
    void add(final nsIURI p0, final String p1, final long p2);
    
    void remove(final String p0, final String p1);
    
    void removeAll();
    
    long testPermission(final nsIURI p0, final String p1);
    
    nsISimpleEnumerator getEnumerator();
}
