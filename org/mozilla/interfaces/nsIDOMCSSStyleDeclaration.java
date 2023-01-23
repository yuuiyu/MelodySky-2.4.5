//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDOMCSSStyleDeclaration extends nsISupports
{
    public static final String NS_IDOMCSSSTYLEDECLARATION_IID = "{a6cf90be-15b3-11d2-932e-00805f8add32}";
    
    String getCssText();
    
    void setCssText(final String p0);
    
    String getPropertyValue(final String p0);
    
    nsIDOMCSSValue getPropertyCSSValue(final String p0);
    
    String removeProperty(final String p0);
    
    String getPropertyPriority(final String p0);
    
    void setProperty(final String p0, final String p1, final String p2);
    
    long getLength();
    
    String item(final long p0);
    
    nsIDOMCSSRule getParentRule();
}
