//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDOMHTMLInputElement extends nsIDOMHTMLElement
{
    public static final String NS_IDOMHTMLINPUTELEMENT_IID = "{a6cf9093-15b3-11d2-932e-00805f8add32}";
    
    String getDefaultValue();
    
    void setDefaultValue(final String p0);
    
    boolean getDefaultChecked();
    
    void setDefaultChecked(final boolean p0);
    
    nsIDOMHTMLFormElement getForm();
    
    String getAccept();
    
    void setAccept(final String p0);
    
    String getAccessKey();
    
    void setAccessKey(final String p0);
    
    String getAlign();
    
    void setAlign(final String p0);
    
    String getAlt();
    
    void setAlt(final String p0);
    
    boolean getChecked();
    
    void setChecked(final boolean p0);
    
    boolean getDisabled();
    
    void setDisabled(final boolean p0);
    
    int getMaxLength();
    
    void setMaxLength(final int p0);
    
    String getName();
    
    void setName(final String p0);
    
    boolean getReadOnly();
    
    void setReadOnly(final boolean p0);
    
    long getSize();
    
    void setSize(final long p0);
    
    String getSrc();
    
    void setSrc(final String p0);
    
    int getTabIndex();
    
    void setTabIndex(final int p0);
    
    String getType();
    
    void setType(final String p0);
    
    String getUseMap();
    
    void setUseMap(final String p0);
    
    String getValue();
    
    void setValue(final String p0);
    
    void blur();
    
    void focus();
    
    void select();
    
    void click();
}
