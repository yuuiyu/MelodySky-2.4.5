//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDOMHTMLCanvasElement extends nsIDOMHTMLElement
{
    public static final String NS_IDOMHTMLCANVASELEMENT_IID = "{0583a2ea-ab19-40e1-8be4-5e9b2f275560}";
    
    int getWidth();
    
    void setWidth(final int p0);
    
    int getHeight();
    
    void setHeight(final int p0);
    
    nsISupports getContext(final String p0);
    
    String toDataURL();
}
