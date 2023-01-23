//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsICacheService extends nsISupports
{
    public static final String NS_ICACHESERVICE_IID = "{de114eb4-29fc-4959-b2f7-2d03eb9bc771}";
    
    nsICacheSession createSession(final String p0, final int p1, final boolean p2);
    
    void visitEntries(final nsICacheVisitor p0);
    
    void evictEntries(final int p0);
}
