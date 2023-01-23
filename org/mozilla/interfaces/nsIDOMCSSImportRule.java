//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDOMCSSImportRule extends nsIDOMCSSRule
{
    public static final String NS_IDOMCSSIMPORTRULE_IID = "{a6cf90cf-15b3-11d2-932e-00805f8add32}";
    
    String getHref();
    
    nsIDOMMediaList getMedia();
    
    nsIDOMCSSStyleSheet getStyleSheet();
}
