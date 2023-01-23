//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDOMHTMLTableRowElement extends nsIDOMHTMLElement
{
    public static final String NS_IDOMHTMLTABLEROWELEMENT_IID = "{a6cf90b6-15b3-11d2-932e-00805f8add32}";
    
    int getRowIndex();
    
    int getSectionRowIndex();
    
    nsIDOMHTMLCollection getCells();
    
    String getAlign();
    
    void setAlign(final String p0);
    
    String getBgColor();
    
    void setBgColor(final String p0);
    
    String getCh();
    
    void setCh(final String p0);
    
    String getChOff();
    
    void setChOff(final String p0);
    
    String getVAlign();
    
    void setVAlign(final String p0);
    
    nsIDOMHTMLElement insertCell(final int p0);
    
    void deleteCell(final int p0);
}
