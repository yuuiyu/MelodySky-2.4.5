//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDOMLSOutput extends nsISupports
{
    public static final String NS_IDOMLSOUTPUT_IID = "{757e9971-8890-478d-a53a-07f9f6f6e0d3}";
    
    nsISupports getCharacterStream();
    
    void setCharacterStream(final nsISupports p0);
    
    nsIOutputStream getByteStream();
    
    void setByteStream(final nsIOutputStream p0);
    
    String getSystemId();
    
    void setSystemId(final String p0);
    
    String getEncoding();
    
    void setEncoding(final String p0);
}
