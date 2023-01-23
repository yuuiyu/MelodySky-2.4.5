//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsICookiePermission extends nsISupports
{
    public static final String NS_ICOOKIEPERMISSION_IID = "{91f1c3ec-73a0-4bf0-bdc5-348a1f181b0e}";
    public static final int ACCESS_DEFAULT = 0;
    public static final int ACCESS_ALLOW = 1;
    public static final int ACCESS_DENY = 2;
    public static final int ACCESS_SESSION = 8;
    
    void setAccess(final nsIURI p0, final int p1);
    
    int canAccess(final nsIURI p0, final nsIURI p1, final nsIChannel p2);
    
    boolean canSetCookie(final nsIURI p0, final nsIChannel p1, final nsICookie2 p2, final boolean[] p3, final long[] p4);
}
