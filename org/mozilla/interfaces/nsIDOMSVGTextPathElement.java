//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDOMSVGTextPathElement extends nsIDOMSVGTextContentElement
{
    public static final String NS_IDOMSVGTEXTPATHELEMENT_IID = "{5c29a76c-3489-48fe-b9ea-ea0f5b196dff}";
    public static final int TEXTPATH_METHODTYPE_UNKNOWN = 0;
    public static final int TEXTPATH_METHODTYPE_ALIGN = 1;
    public static final int TEXTPATH_METHODTYPE_STRETCH = 2;
    public static final int TEXTPATH_SPACINGTYPE_UNKNOWN = 0;
    public static final int TEXTPATH_SPACINGTYPE_AUTO = 1;
    public static final int TEXTPATH_SPACINGTYPE_EXACT = 2;
    
    nsIDOMSVGAnimatedLength getStartOffset();
    
    nsIDOMSVGAnimatedEnumeration getMethod();
    
    nsIDOMSVGAnimatedEnumeration getSpacing();
}
