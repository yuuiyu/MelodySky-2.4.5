//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsICookie2 extends nsICookie
{
    public static final String NS_ICOOKIE2_IID = "{d3493503-7854-46ed-8284-8af54a847efb}";
    
    String getRawHost();
    
    boolean getIsSession();
    
    long getExpiry();
}
