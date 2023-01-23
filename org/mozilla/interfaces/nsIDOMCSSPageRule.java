//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDOMCSSPageRule extends nsIDOMCSSRule
{
    public static final String NS_IDOMCSSPAGERULE_IID = "{a6cf90bd-15b3-11d2-932e-00805f8add32}";
    
    String getSelectorText();
    
    void setSelectorText(final String p0);
    
    nsIDOMCSSStyleDeclaration getStyle();
}
