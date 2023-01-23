//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIJARURI extends nsIURL
{
    public static final String NS_IJARURI_IID = "{c7e410d3-85f2-11d3-9f63-006008a6efe9}";
    
    nsIURI getJARFile();
    
    void setJARFile(final nsIURI p0);
    
    String getJAREntry();
    
    void setJAREntry(final String p0);
}
