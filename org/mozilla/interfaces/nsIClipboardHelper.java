//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIClipboardHelper extends nsISupports
{
    public static final String NS_ICLIPBOARDHELPER_IID = "{44073a98-1dd2-11b2-8600-d0ae854dbe93}";
    
    void copyStringToClipboard(final String p0, final int p1);
    
    void copyString(final String p0);
}
