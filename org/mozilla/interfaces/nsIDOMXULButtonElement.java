//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDOMXULButtonElement extends nsIDOMXULLabeledControlElement
{
    public static final String NS_IDOMXULBUTTONELEMENT_IID = "{6852d9a6-1dd2-11b2-a29d-cd7977a91b1b}";
    public static final short CHECKSTATE_UNCHECKED = 0;
    public static final short CHECKSTATE_CHECKED = 1;
    public static final short CHECKSTATE_MIXED = 2;
    
    String getType();
    
    void setType(final String p0);
    
    String getDlgType();
    
    void setDlgType(final String p0);
    
    boolean getOpen();
    
    void setOpen(final boolean p0);
    
    boolean getChecked();
    
    void setChecked(final boolean p0);
    
    int getCheckState();
    
    void setCheckState(final int p0);
    
    boolean getAutoCheck();
    
    void setAutoCheck(final boolean p0);
    
    String getGroup();
    
    void setGroup(final String p0);
}
