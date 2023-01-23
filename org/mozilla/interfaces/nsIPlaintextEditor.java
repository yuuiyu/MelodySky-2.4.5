//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIPlaintextEditor extends nsISupports
{
    public static final String NS_IPLAINTEXTEDITOR_IID = "{b5f39ed4-1dd1-11b2-9d00-fd54d6f54962}";
    public static final short eEditorPlaintextBit = 0;
    public static final short eEditorSingleLineBit = 1;
    public static final short eEditorPasswordBit = 2;
    public static final short eEditorReadonlyBit = 3;
    public static final short eEditorDisabledBit = 4;
    public static final short eEditorFilterInputBit = 5;
    public static final short eEditorMailBit = 6;
    public static final short eEditorUseAsyncUpdatesBit = 7;
    public static final short eEditorEnableWrapHackBit = 8;
    public static final short eEditorWidgetBit = 9;
    public static final short eEditorNoCSSBit = 10;
    public static final int eEditorPlaintextMask = 1;
    public static final int eEditorSingleLineMask = 2;
    public static final int eEditorPasswordMask = 4;
    public static final int eEditorReadonlyMask = 8;
    public static final int eEditorDisabledMask = 16;
    public static final int eEditorFilterInputMask = 32;
    public static final int eEditorMailMask = 64;
    public static final int eEditorUseAsyncUpdatesMask = 128;
    public static final int eEditorEnableWrapHackMask = 256;
    public static final int eEditorWidgetMask = 512;
    public static final int eEditorNoCSSMask = 1024;
    
    int getTextLength();
    
    int getMaxTextLength();
    
    void setMaxTextLength(final int p0);
    
    int getWrapWidth();
    
    void setWrapWidth(final int p0);
    
    void insertText(final String p0);
    
    void insertLineBreak();
}
