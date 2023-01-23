//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIExpatSink extends nsISupports
{
    public static final String NS_IEXPATSINK_IID = "{1deea160-c661-11d5-84cc-0010a4e0c706}";
    
    void handleStartElement(final String p0, final String[] p1, final long p2, final int p3, final long p4);
    
    void handleEndElement(final String p0);
    
    void handleComment(final String p0);
    
    void handleCDataSection(final String p0, final long p1);
    
    void handleDoctypeDecl(final String p0, final String p1, final String p2, final String p3, final nsISupports p4);
    
    void handleCharacterData(final String p0, final long p1);
    
    void handleProcessingInstruction(final String p0, final String p1);
    
    void handleXMLDeclaration(final String p0, final String p1, final int p2);
    
    void reportError(final String p0, final String p1);
}
