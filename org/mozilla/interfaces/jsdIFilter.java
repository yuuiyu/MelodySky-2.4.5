//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface jsdIFilter extends nsISupports
{
    public static final String JSDIFILTER_IID = "{05593438-1b83-4517-864f-3cea3d37a266}";
    public static final long FLAG_RESERVED_MASK = 255L;
    public static final long FLAG_ENABLED = 1L;
    public static final long FLAG_PASS = 2L;
    
    long getFlags();
    
    void setFlags(final long p0);
    
    nsISupports getGlobalObject();
    
    void setGlobalObject(final nsISupports p0);
    
    String getUrlPattern();
    
    void setUrlPattern(final String p0);
    
    long getStartLine();
    
    void setStartLine(final long p0);
    
    long getEndLine();
    
    void setEndLine(final long p0);
}
