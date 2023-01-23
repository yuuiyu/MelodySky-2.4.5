//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDOMHTMLTextAreaElement extends nsIDOMHTMLElement
{
    public static final String NS_IDOMHTMLTEXTAREAELEMENT_IID = "{a6cf9094-15b3-11d2-932e-00805f8add32}";
    
    String getDefaultValue();
    
    void setDefaultValue(final String p0);
    
    nsIDOMHTMLFormElement getForm();
    
    String getAccessKey();
    
    void setAccessKey(final String p0);
    
    int getCols();
    
    void setCols(final int p0);
    
    boolean getDisabled();
    
    void setDisabled(final boolean p0);
    
    String getName();
    
    void setName(final String p0);
    
    boolean getReadOnly();
    
    void setReadOnly(final boolean p0);
    
    int getRows();
    
    void setRows(final int p0);
    
    int getTabIndex();
    
    void setTabIndex(final int p0);
    
    String getType();
    
    String getValue();
    
    void setValue(final String p0);
    
    void blur();
    
    void focus();
    
    void select();
}
