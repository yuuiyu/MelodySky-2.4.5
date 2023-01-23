//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDirIndex extends nsISupports
{
    public static final String NS_IDIRINDEX_IID = "{23bbabd0-1dd2-11b2-86b7-aad68ae7d7e0}";
    public static final long TYPE_UNKNOWN = 0L;
    public static final long TYPE_DIRECTORY = 1L;
    public static final long TYPE_FILE = 2L;
    public static final long TYPE_SYMLINK = 3L;
    
    long getType();
    
    void setType(final long p0);
    
    String getContentType();
    
    void setContentType(final String p0);
    
    String getLocation();
    
    void setLocation(final String p0);
    
    String getDescription();
    
    void setDescription(final String p0);
    
    long getSize();
    
    void setSize(final long p0);
    
    double getLastModified();
    
    void setLastModified(final double p0);
}
