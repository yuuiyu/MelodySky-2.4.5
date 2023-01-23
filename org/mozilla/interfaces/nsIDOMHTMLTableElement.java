//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDOMHTMLTableElement extends nsIDOMHTMLElement
{
    public static final String NS_IDOMHTMLTABLEELEMENT_IID = "{a6cf90b2-15b3-11d2-932e-00805f8add32}";
    
    nsIDOMHTMLTableCaptionElement getCaption();
    
    void setCaption(final nsIDOMHTMLTableCaptionElement p0);
    
    nsIDOMHTMLTableSectionElement getTHead();
    
    void setTHead(final nsIDOMHTMLTableSectionElement p0);
    
    nsIDOMHTMLTableSectionElement getTFoot();
    
    void setTFoot(final nsIDOMHTMLTableSectionElement p0);
    
    nsIDOMHTMLCollection getRows();
    
    nsIDOMHTMLCollection getTBodies();
    
    String getAlign();
    
    void setAlign(final String p0);
    
    String getBgColor();
    
    void setBgColor(final String p0);
    
    String getBorder();
    
    void setBorder(final String p0);
    
    String getCellPadding();
    
    void setCellPadding(final String p0);
    
    String getCellSpacing();
    
    void setCellSpacing(final String p0);
    
    String getFrame();
    
    void setFrame(final String p0);
    
    String getRules();
    
    void setRules(final String p0);
    
    String getSummary();
    
    void setSummary(final String p0);
    
    String getWidth();
    
    void setWidth(final String p0);
    
    nsIDOMHTMLElement createTHead();
    
    void deleteTHead();
    
    nsIDOMHTMLElement createTFoot();
    
    void deleteTFoot();
    
    nsIDOMHTMLElement createCaption();
    
    void deleteCaption();
    
    nsIDOMHTMLElement insertRow(final int p0);
    
    void deleteRow(final int p0);
}
