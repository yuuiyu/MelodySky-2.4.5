//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIURL extends nsIURI
{
    public static final String NS_IURL_IID = "{d6116970-8034-11d3-9399-00104ba0fd40}";
    
    String getFilePath();
    
    void setFilePath(final String p0);
    
    String getParam();
    
    void setParam(final String p0);
    
    String getQuery();
    
    void setQuery(final String p0);
    
    String getRef();
    
    void setRef(final String p0);
    
    String getDirectory();
    
    void setDirectory(final String p0);
    
    String getFileName();
    
    void setFileName(final String p0);
    
    String getFileBaseName();
    
    void setFileBaseName(final String p0);
    
    String getFileExtension();
    
    void setFileExtension(final String p0);
    
    String getCommonBaseSpec(final nsIURI p0);
    
    String getRelativeSpec(final nsIURI p0);
}
