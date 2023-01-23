//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDOMStyleSheetList extends nsISupports
{
    public static final String NS_IDOMSTYLESHEETLIST_IID = "{a6cf9081-15b3-11d2-932e-00805f8add32}";
    
    long getLength();
    
    nsIDOMStyleSheet item(final long p0);
}
