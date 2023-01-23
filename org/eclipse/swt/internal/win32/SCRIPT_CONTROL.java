//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.win32;

public class SCRIPT_CONTROL
{
    public int uDefaultLanguage;
    public boolean fContextDigits;
    public boolean fInvertPreBoundDir;
    public boolean fInvertPostBoundDir;
    public boolean fLinkStringBefore;
    public boolean fLinkStringAfter;
    public boolean fNeutralOverride;
    public boolean fNumericOverride;
    public boolean fLegacyBidiClass;
    public boolean fMergeNeutralItems;
    public boolean fUseStandardBidi;
    public int fReserved;
    public static final int sizeof;
    
    static {
        sizeof = OS.SCRIPT_CONTROL_sizeof();
    }
}
