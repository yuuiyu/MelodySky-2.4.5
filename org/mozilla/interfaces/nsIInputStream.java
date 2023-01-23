//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIInputStream extends nsISupports
{
    public static final String NS_IINPUTSTREAM_IID = "{fa9c7f6c-61b3-11d4-9877-00c04fa0cf4a}";
    
    void close();
    
    long available();
    
    boolean isNonBlocking();
}
