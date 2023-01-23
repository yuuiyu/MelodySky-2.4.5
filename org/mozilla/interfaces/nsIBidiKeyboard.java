//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIBidiKeyboard extends nsISupports
{
    public static final String NS_IBIDIKEYBOARD_IID = "{bb961ae1-7432-11d4-b77a-00104b4119f8}";
    
    void isLangRTL(final boolean[] p0);
    
    void setLangFromBidiLevel(final short p0);
}
