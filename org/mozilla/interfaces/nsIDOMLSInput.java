//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDOMLSInput extends nsISupports
{
    public static final String NS_IDOMLSINPUT_IID = "{165e7f61-5048-4c2c-b4bf-6b44bb617ee4}";
    
    nsISupports getCharacterStream();
    
    void setCharacterStream(final nsISupports p0);
    
    nsIInputStream getByteStream();
    
    void setByteStream(final nsIInputStream p0);
    
    String getStringData();
    
    void setStringData(final String p0);
    
    String getSystemId();
    
    void setSystemId(final String p0);
    
    String getPublicId();
    
    void setPublicId(final String p0);
    
    String getBaseURI();
    
    void setBaseURI(final String p0);
    
    String getEncoding();
    
    void setEncoding(final String p0);
    
    boolean getCertifiedText();
    
    void setCertifiedText(final boolean p0);
}
