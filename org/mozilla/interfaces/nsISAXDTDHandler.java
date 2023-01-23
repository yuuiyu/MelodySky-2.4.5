//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsISAXDTDHandler extends nsISupports
{
    public static final String NS_ISAXDTDHANDLER_IID = "{4d01f225-6cc5-11da-be43-001422106990}";
    
    void notationDecl(final String p0, final String p1, final String p2);
    
    void unparsedEntityDecl(final String p0, final String p1, final String p2, final String p3);
}
