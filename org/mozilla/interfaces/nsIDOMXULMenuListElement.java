//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDOMXULMenuListElement extends nsIDOMXULSelectControlElement
{
    public static final String NS_IDOMXULMENULISTELEMENT_IID = "{3d49950e-04f9-4e35-a9a0-ffd51356a674}";
    
    boolean getEditable();
    
    void setEditable(final boolean p0);
    
    boolean getOpen();
    
    void setOpen(final boolean p0);
    
    String getLabel();
    
    String getCrop();
    
    void setCrop(final String p0);
    
    String getImage();
    
    void setImage(final String p0);
    
    nsIDOMXULTextBoxElement getInputField();
}
