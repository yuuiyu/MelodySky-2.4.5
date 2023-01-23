//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDNSService extends nsISupports
{
    public static final String NS_IDNSSERVICE_IID = "{5c8ec09d-bfbf-4eaf-8a36-0d84b5c8f35b}";
    public static final long RESOLVE_BYPASS_CACHE = 1L;
    public static final long RESOLVE_CANONICAL_NAME = 2L;
    
    nsICancelable asyncResolve(final String p0, final long p1, final nsIDNSListener p2, final nsIEventTarget p3);
    
    nsIDNSRecord resolve(final String p0, final long p1);
    
    String getMyHostName();
}
