//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIFastLoadFileControl extends nsISupports
{
    public static final String NS_IFASTLOADFILECONTROL_IID = "{8a1e2c63-af50-4147-af7e-26289dc180dd}";
    
    long getChecksum();
    
    void setChecksum(final long p0);
    
    void startMuxedDocument(final nsISupports p0, final String p1);
    
    nsISupports selectMuxedDocument(final nsISupports p0);
    
    void endMuxedDocument(final nsISupports p0);
    
    boolean hasMuxedDocument(final String p0);
}
