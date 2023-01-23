//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDOMHTMLSelectElement extends nsIDOMHTMLElement
{
    public static final String NS_IDOMHTMLSELECTELEMENT_IID = "{a6cf9090-15b3-11d2-932e-00805f8add32}";
    
    String getType();
    
    int getSelectedIndex();
    
    void setSelectedIndex(final int p0);
    
    String getValue();
    
    void setValue(final String p0);
    
    long getLength();
    
    void setLength(final long p0);
    
    nsIDOMHTMLFormElement getForm();
    
    nsIDOMHTMLOptionsCollection getOptions();
    
    boolean getDisabled();
    
    void setDisabled(final boolean p0);
    
    boolean getMultiple();
    
    void setMultiple(final boolean p0);
    
    String getName();
    
    void setName(final String p0);
    
    int getSize();
    
    void setSize(final int p0);
    
    int getTabIndex();
    
    void setTabIndex(final int p0);
    
    void add(final nsIDOMHTMLElement p0, final nsIDOMHTMLElement p1);
    
    void remove(final int p0);
    
    void blur();
    
    void focus();
}
