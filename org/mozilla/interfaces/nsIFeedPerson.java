//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIFeedPerson extends nsIFeedElementBase
{
    public static final String NS_IFEEDPERSON_IID = "{29cbd45f-f2d3-4b28-b557-3ab7a61ecde4}";
    
    String getName();
    
    void setName(final String p0);
    
    String getEmail();
    
    void setEmail(final String p0);
    
    nsIURI getUri();
    
    void setUri(final nsIURI p0);
}
