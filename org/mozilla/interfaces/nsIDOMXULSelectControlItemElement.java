//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDOMXULSelectControlItemElement extends nsIDOMXULElement
{
    public static final String NS_IDOMXULSELECTCONTROLITEMELEMENT_IID = "{6aaaa30d-54ab-434a-8ae8-6d29a566d870}";
    
    boolean getDisabled();
    
    void setDisabled(final boolean p0);
    
    String getCrop();
    
    void setCrop(final String p0);
    
    String getImage();
    
    void setImage(final String p0);
    
    String getLabel();
    
    void setLabel(final String p0);
    
    String getAccessKey();
    
    void setAccessKey(final String p0);
    
    String getCommand();
    
    void setCommand(final String p0);
    
    String getValue();
    
    void setValue(final String p0);
    
    boolean getSelected();
    
    nsIDOMXULSelectControlElement getControl();
}
