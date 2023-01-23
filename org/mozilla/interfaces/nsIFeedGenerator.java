//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIFeedGenerator extends nsIFeedElementBase
{
    public static final String NS_IFEEDGENERATOR_IID = "{0fecd56b-bd92-481b-a486-b8d489cdd385}";
    
    String getAgent();
    
    void setAgent(final String p0);
    
    String getVersion();
    
    void setVersion(final String p0);
    
    nsIURI getUri();
    
    void setUri(final nsIURI p0);
}
