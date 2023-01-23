//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsISelectionDisplay extends nsISupports
{
    public static final String NS_ISELECTIONDISPLAY_IID = "{0ddf9e1c-1dd2-11b2-a183-908a08aa75ae}";
    public static final short DISPLAY_TEXT = 1;
    public static final short DISPLAY_IMAGES = 2;
    public static final short DISPLAY_FRAMES = 4;
    public static final short DISPLAY_ALL = 7;
    
    void setSelectionFlags(final short p0);
    
    short getSelectionFlags();
}
