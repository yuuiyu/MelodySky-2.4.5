//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsICurrentCharsetListener extends nsISupports
{
    public static final String NS_ICURRENTCHARSETLISTENER_IID = "{cf9428c1-df50-11d3-9d0c-0050040007b2}";
    
    void setCurrentCharset(final String p0);
    
    void setCurrentMailCharset(final String p0);
    
    void setCurrentComposerCharset(final String p0);
}
