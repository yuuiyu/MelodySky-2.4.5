//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDOMNSDocument extends nsISupports
{
    public static final String NS_IDOMNSDOCUMENT_IID = "{a6cf90cd-15b3-11d2-932e-00805f8add32}";
    
    String getCharacterSet();
    
    String getDir();
    
    void setDir(final String p0);
    
    nsIDOMLocation getLocation();
    
    String getTitle();
    
    void setTitle(final String p0);
    
    String getContentType();
    
    String getLastModified();
    
    String getReferrer();
    
    nsIBoxObject getBoxObjectFor(final nsIDOMElement p0);
    
    void setBoxObjectFor(final nsIDOMElement p0, final nsIBoxObject p1);
}
