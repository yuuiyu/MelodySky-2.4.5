//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDOMXULCheckboxElement extends nsIDOMXULLabeledControlElement
{
    public static final String NS_IDOMXULCHECKBOXELEMENT_IID = "{5afaba88-1dd2-11b2-9249-dd65a129d0e4}";
    public static final short CHECKSTATE_UNCHECKED = 0;
    public static final short CHECKSTATE_CHECKED = 1;
    public static final short CHECKSTATE_MIXED = 2;
    
    boolean getChecked();
    
    void setChecked(final boolean p0);
    
    int getCheckState();
    
    void setCheckState(final int p0);
    
    boolean getAutoCheck();
    
    void setAutoCheck(final boolean p0);
}
