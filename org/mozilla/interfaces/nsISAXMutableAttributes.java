//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsISAXMutableAttributes extends nsISAXAttributes
{
    public static final String NS_ISAXMUTABLEATTRIBUTES_IID = "{8b1de83d-cebb-49fa-8245-c0fe319eb7b6}";
    
    void addAttribute(final String p0, final String p1, final String p2, final String p3, final String p4);
    
    void clear();
    
    void removeAttribute(final long p0);
    
    void setAttributes(final nsISAXAttributes p0);
    
    void setAttribute(final long p0, final String p1, final String p2, final String p3, final String p4, final String p5);
    
    void setLocalName(final long p0, final String p1);
    
    void setQName(final long p0, final String p1);
    
    void setType(final long p0, final String p1);
    
    void setURI(final long p0, final String p1);
    
    void setValue(final long p0, final String p1);
}
