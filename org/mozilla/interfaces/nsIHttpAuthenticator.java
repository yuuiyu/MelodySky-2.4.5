//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIHttpAuthenticator extends nsISupports
{
    public static final String NS_IHTTPAUTHENTICATOR_IID = "{0f331436-8bc8-4c68-a124-d0253a19d06f}";
    public static final long REQUEST_BASED = 1L;
    public static final long CONNECTION_BASED = 2L;
    public static final long REUSABLE_CREDENTIALS = 4L;
    public static final long REUSABLE_CHALLENGE = 8L;
    public static final long IDENTITY_IGNORED = 1024L;
    public static final long IDENTITY_INCLUDES_DOMAIN = 2048L;
    
    void challengeReceived(final nsIHttpChannel p0, final String p1, final boolean p2, final nsISupports[] p3, final nsISupports[] p4, final boolean[] p5);
    
    String generateCredentials(final nsIHttpChannel p0, final String p1, final boolean p2, final String p3, final String p4, final String p5, final nsISupports[] p6, final nsISupports[] p7);
    
    long getAuthFlags();
}
