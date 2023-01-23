//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIExtendedExpatSink extends nsIExpatSink
{
    public static final String NS_IEXTENDEDEXPATSINK_IID = "{0c2dc80f-7aa4-467a-9454-b89dba0e0779}";
    
    void handleStartDTD(final String p0, final String p1, final String p2);
    
    void handleStartNamespaceDecl(final String p0, final String p1);
    
    void handleEndNamespaceDecl(final String p0);
    
    void handleNotationDecl(final String p0, final String p1, final String p2);
    
    void handleUnparsedEntityDecl(final String p0, final String p1, final String p2, final String p3);
}
