//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDOMHTMLOptionElement extends nsIDOMHTMLElement
{
    public static final String NS_IDOMHTMLOPTIONELEMENT_IID = "{a6cf9092-15b3-11d2-932e-00805f8add32}";
    
    nsIDOMHTMLFormElement getForm();
    
    boolean getDefaultSelected();
    
    void setDefaultSelected(final boolean p0);
    
    String getText();
    
    int getIndex();
    
    boolean getDisabled();
    
    void setDisabled(final boolean p0);
    
    String getLabel();
    
    void setLabel(final String p0);
    
    boolean getSelected();
    
    void setSelected(final boolean p0);
    
    String getValue();
    
    void setValue(final String p0);
}
