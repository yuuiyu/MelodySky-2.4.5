//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIPopupBoxObject extends nsISupports
{
    public static final String NS_IPOPUPBOXOBJECT_IID = "{33c60e14-5150-4876-9a96-2732557e6895}";
    
    void showPopup(final nsIDOMElement p0, final nsIDOMElement p1, final int p2, final int p3, final String p4, final String p5, final String p6);
    
    void hidePopup();
    
    boolean getAutoPosition();
    
    void setAutoPosition(final boolean p0);
    
    void enableKeyboardNavigator(final boolean p0);
    
    void enableRollup(final boolean p0);
    
    void sizeTo(final int p0, final int p1);
    
    void moveTo(final int p0, final int p1);
}
