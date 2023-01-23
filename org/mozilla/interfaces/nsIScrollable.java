//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIScrollable extends nsISupports
{
    public static final String NS_ISCROLLABLE_IID = "{919e792a-6490-40b8-bba5-f9e9ad5640c8}";
    public static final int ScrollOrientation_X = 1;
    public static final int ScrollOrientation_Y = 2;
    public static final int Scrollbar_Auto = 1;
    public static final int Scrollbar_Never = 2;
    public static final int Scrollbar_Always = 3;
    
    int getCurScrollPos(final int p0);
    
    void setCurScrollPos(final int p0, final int p1);
    
    void setCurScrollPosEx(final int p0, final int p1);
    
    void getScrollRange(final int p0, final int[] p1, final int[] p2);
    
    void setScrollRange(final int p0, final int p1, final int p2);
    
    void setScrollRangeEx(final int p0, final int p1, final int p2, final int p3);
    
    int getDefaultScrollbarPreferences(final int p0);
    
    void setDefaultScrollbarPreferences(final int p0, final int p1);
    
    void getScrollbarVisibility(final boolean[] p0, final boolean[] p1);
}
