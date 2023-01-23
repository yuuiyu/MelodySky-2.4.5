//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDOMHTMLFrameElement extends nsIDOMHTMLElement
{
    public static final String NS_IDOMHTMLFRAMEELEMENT_IID = "{a6cf90b9-15b3-11d2-932e-00805f8add32}";
    
    String getFrameBorder();
    
    void setFrameBorder(final String p0);
    
    String getLongDesc();
    
    void setLongDesc(final String p0);
    
    String getMarginHeight();
    
    void setMarginHeight(final String p0);
    
    String getMarginWidth();
    
    void setMarginWidth(final String p0);
    
    String getName();
    
    void setName(final String p0);
    
    boolean getNoResize();
    
    void setNoResize(final boolean p0);
    
    String getScrolling();
    
    void setScrolling(final String p0);
    
    String getSrc();
    
    void setSrc(final String p0);
    
    nsIDOMDocument getContentDocument();
}
