//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIFeedElementBase extends nsISupports
{
    public static final String NS_IFEEDELEMENTBASE_IID = "{5215291e-fa0a-40c2-8ce7-e86cd1a1d3fa}";
    
    nsISAXAttributes getAttributes();
    
    void setAttributes(final nsISAXAttributes p0);
    
    nsIURI getBaseURI();
    
    void setBaseURI(final nsIURI p0);
}
