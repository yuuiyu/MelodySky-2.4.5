//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDOMXULSelectControlElement extends nsIDOMXULControlElement
{
    public static final String NS_IDOMXULSELECTCONTROLELEMENT_IID = "{59fec127-2a0e-445b-84b5-a66dc90245db}";
    
    nsIDOMXULSelectControlItemElement getSelectedItem();
    
    void setSelectedItem(final nsIDOMXULSelectControlItemElement p0);
    
    int getSelectedIndex();
    
    void setSelectedIndex(final int p0);
    
    String getValue();
    
    void setValue(final String p0);
    
    nsIDOMXULSelectControlItemElement appendItem(final String p0, final String p1);
    
    nsIDOMXULSelectControlItemElement insertItemAt(final int p0, final String p1, final String p2);
    
    nsIDOMXULSelectControlItemElement removeItemAt(final int p0);
}
