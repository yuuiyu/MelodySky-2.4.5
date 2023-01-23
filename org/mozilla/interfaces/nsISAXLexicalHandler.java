//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsISAXLexicalHandler extends nsISupports
{
    public static final String NS_ISAXLEXICALHANDLER_IID = "{23c26a56-adff-440c-8caf-95c2dc2e399b}";
    
    void comment(final String p0);
    
    void startDTD(final String p0, final String p1, final String p2);
    
    void endDTD();
    
    void startCDATA();
    
    void endCDATA();
    
    void startEntity(final String p0);
    
    void endEntity(final String p0);
}
