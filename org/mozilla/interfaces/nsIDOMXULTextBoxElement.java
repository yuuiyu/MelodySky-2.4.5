//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDOMXULTextBoxElement extends nsIDOMXULControlElement
{
    public static final String NS_IDOMXULTEXTBOXELEMENT_IID = "{71135b6c-294e-4634-a8e4-a72398f1e72a}";
    
    nsIDOMNode getInputField();
    
    int getTextLength();
    
    int getMaxLength();
    
    void setMaxLength(final int p0);
    
    int getSize();
    
    void setSize(final int p0);
    
    int getSelectionStart();
    
    void setSelectionStart(final int p0);
    
    int getSelectionEnd();
    
    void setSelectionEnd(final int p0);
    
    String getValue();
    
    void setValue(final String p0);
    
    String getType();
    
    void setType(final String p0);
    
    void select();
    
    void setSelectionRange(final int p0, final int p1);
}
