//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDOMParser extends nsISupports
{
    public static final String NS_IDOMPARSER_IID = "{4f45513e-55e5-411c-a844-e899057026c1}";
    
    nsIDOMDocument parseFromString(final String p0, final String p1);
    
    nsIDOMDocument parseFromBuffer(final byte[] p0, final long p1, final String p2);
    
    nsIDOMDocument parseFromStream(final nsIInputStream p0, final String p1, final int p2, final String p3);
}
