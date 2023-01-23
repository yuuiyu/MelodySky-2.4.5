//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIResProtocolHandler extends nsIProtocolHandler
{
    public static final String NS_IRESPROTOCOLHANDLER_IID = "{067ca872-e947-4bd6-8946-a479cb6ba5dd}";
    
    void setSubstitution(final String p0, final nsIURI p1);
    
    nsIURI getSubstitution(final String p0);
    
    boolean hasSubstitution(final String p0);
    
    String resolveURI(final nsIURI p0);
}
