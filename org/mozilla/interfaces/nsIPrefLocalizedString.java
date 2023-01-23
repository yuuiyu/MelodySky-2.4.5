//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIPrefLocalizedString extends nsISupports
{
    public static final String NS_IPREFLOCALIZEDSTRING_IID = "{ae419e24-1dd1-11b2-b39a-d3e5e7073802}";
    
    String getData();
    
    void setData(final String p0);
    
    String toString();
    
    void setDataWithLength(final long p0, final String p1);
}
