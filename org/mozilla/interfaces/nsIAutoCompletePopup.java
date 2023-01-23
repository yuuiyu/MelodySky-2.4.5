//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIAutoCompletePopup extends nsISupports
{
    public static final String NS_IAUTOCOMPLETEPOPUP_IID = "{65f6cd46-22ec-4329-bb3b-bcd1103f2204}";
    
    nsIAutoCompleteInput getInput();
    
    String getOverrideValue();
    
    int getSelectedIndex();
    
    void setSelectedIndex(final int p0);
    
    boolean getPopupOpen();
    
    void openPopup(final nsIAutoCompleteInput p0, final int p1, final int p2, final int p3);
    
    void closePopup();
    
    void invalidate();
    
    void selectBy(final boolean p0, final boolean p1);
}
