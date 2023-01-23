//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.win32;

public class SCRIPT_PROPERTIES
{
    public short langid;
    public boolean fNumeric;
    public boolean fComplex;
    public boolean fNeedsWordBreaking;
    public boolean fNeedsCaretInfo;
    public byte bCharSet;
    public boolean fControl;
    public boolean fPrivateUseArea;
    public boolean fNeedsCharacterJustify;
    public boolean fInvalidGlyph;
    public boolean fInvalidLogAttr;
    public boolean fCDM;
    public boolean fAmbiguousCharSet;
    public boolean fClusterSizeVaries;
    public boolean fRejectInvalid;
    public static final int sizeof;
    
    static {
        sizeof = OS.SCRIPT_PROPERTIES_sizeof();
    }
}
