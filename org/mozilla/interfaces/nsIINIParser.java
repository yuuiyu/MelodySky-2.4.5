//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIINIParser extends nsISupports
{
    public static final String NS_IINIPARSER_IID = "{7eb955f6-3e78-4d39-b72f-c1bf12a94bce}";
    
    nsIUTF8StringEnumerator getSections();
    
    nsIUTF8StringEnumerator getKeys(final String p0);
    
    String getString(final String p0, final String p1);
}
