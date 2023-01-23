//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDOMCSSRule extends nsISupports
{
    public static final String NS_IDOMCSSRULE_IID = "{a6cf90c1-15b3-11d2-932e-00805f8add32}";
    public static final int UNKNOWN_RULE = 0;
    public static final int STYLE_RULE = 1;
    public static final int CHARSET_RULE = 2;
    public static final int IMPORT_RULE = 3;
    public static final int MEDIA_RULE = 4;
    public static final int FONT_FACE_RULE = 5;
    public static final int PAGE_RULE = 6;
    
    int getType();
    
    String getCssText();
    
    void setCssText(final String p0);
    
    nsIDOMCSSStyleSheet getParentStyleSheet();
    
    nsIDOMCSSRule getParentRule();
}
