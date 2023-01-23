//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsILocalFile extends nsIFile
{
    public static final String NS_ILOCALFILE_IID = "{aa610f20-a889-11d3-8c81-000064657374}";
    
    void initWithPath(final String p0);
    
    void initWithFile(final nsILocalFile p0);
    
    boolean getFollowLinks();
    
    void setFollowLinks(final boolean p0);
    
    long getDiskSpaceAvailable();
    
    void appendRelativePath(final String p0);
    
    String getPersistentDescriptor();
    
    void setPersistentDescriptor(final String p0);
    
    void reveal();
    
    void launch();
    
    String getRelativeDescriptor(final nsILocalFile p0);
    
    void setRelativeDescriptor(final nsILocalFile p0, final String p1);
}
