//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIUnicharOutputStream extends nsISupports
{
    public static final String NS_IUNICHAROUTPUTSTREAM_IID = "{2d00b1bb-8b21-4a63-bcc6-7213f513ac2e}";
    
    boolean write(final long p0, final int[] p1);
    
    boolean writeString(final String p0);
    
    void flush();
    
    void close();
}
