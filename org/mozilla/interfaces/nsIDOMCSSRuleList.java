//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDOMCSSRuleList extends nsISupports
{
    public static final String NS_IDOMCSSRULELIST_IID = "{a6cf90c0-15b3-11d2-932e-00805f8add32}";
    
    long getLength();
    
    nsIDOMCSSRule item(final long p0);
}
