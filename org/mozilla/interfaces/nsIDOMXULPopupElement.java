//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDOMXULPopupElement extends nsIDOMXULElement
{
    public static final String NS_IDOMXULPOPUPELEMENT_IID = "{c32390a8-2bd8-4d1b-bf9f-1b1d0a944d19}";
    public static final int BEFORE_START = 1;
    public static final int BEFORE_END = 2;
    public static final int AFTER_START = 3;
    public static final int AFTER_END = 4;
    public static final int START_BEFORE = 5;
    public static final int START_AFTER = 6;
    public static final int END_BEFORE = 7;
    public static final int END_AFTER = 8;
    public static final int OVERLAP = 9;
    public static final int AT_POINTER = 10;
    public static final int AFTER_POINTER = 11;
    
    String getPosition();
    
    void setPosition(final String p0);
    
    void showPopup(final int p0, final nsIDOMElement p1, final nsIDOMElement p2);
    
    void hidePopup();
}
