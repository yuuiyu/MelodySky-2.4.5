//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDOMHTMLTableSectionElement extends nsIDOMHTMLElement
{
    public static final String NS_IDOMHTMLTABLESECTIONELEMENT_IID = "{a6cf90b5-15b3-11d2-932e-00805f8add32}";
    
    String getAlign();
    
    void setAlign(final String p0);
    
    String getCh();
    
    void setCh(final String p0);
    
    String getChOff();
    
    void setChOff(final String p0);
    
    String getVAlign();
    
    void setVAlign(final String p0);
    
    nsIDOMHTMLCollection getRows();
    
    nsIDOMHTMLElement insertRow(final int p0);
    
    void deleteRow(final int p0);
}
