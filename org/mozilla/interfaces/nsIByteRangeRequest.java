//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIByteRangeRequest extends nsISupports
{
    public static final String NS_IBYTERANGEREQUEST_IID = "{c1b1f426-7e83-4759-9f88-0e1b17f49366}";
    
    boolean getIsByteRangeRequest();
    
    long getStartRange();
    
    long getEndRange();
}
