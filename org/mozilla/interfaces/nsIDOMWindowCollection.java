//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDOMWindowCollection extends nsISupports
{
    public static final String NS_IDOMWINDOWCOLLECTION_IID = "{a6cf906f-15b3-11d2-932e-00805f8add32}";
    
    long getLength();
    
    nsIDOMWindow item(final long p0);
    
    nsIDOMWindow namedItem(final String p0);
}
