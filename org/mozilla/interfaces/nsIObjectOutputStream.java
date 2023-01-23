//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIObjectOutputStream extends nsIBinaryOutputStream
{
    public static final String NS_IOBJECTOUTPUTSTREAM_IID = "{92c898ac-5fde-4b99-87b3-5d486422094b}";
    
    void writeObject(final nsISupports p0, final boolean p1);
    
    void writeSingleRefObject(final nsISupports p0);
    
    void writeCompoundObject(final nsISupports p0, final String p1, final boolean p2);
    
    void writeID(final String p0);
}
