//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIHTTPIndex extends nsISupports
{
    public static final String NS_IHTTPINDEX_IID = "{6f2bdbd0-58c3-11d3-be36-00104bde6048}";
    
    String getBaseURL();
    
    nsIRDFDataSource getDataSource();
    
    String getEncoding();
    
    void setEncoding(final String p0);
}
