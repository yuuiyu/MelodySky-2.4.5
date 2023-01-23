//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDOMSVGStylable extends nsISupports
{
    public static final String NS_IDOMSVGSTYLABLE_IID = "{ea8a6cb1-9176-45db-989d-d0e89f563d7e}";
    
    nsIDOMSVGAnimatedString getClassName();
    
    nsIDOMCSSStyleDeclaration getStyle();
    
    nsIDOMCSSValue getPresentationAttribute(final String p0);
}
