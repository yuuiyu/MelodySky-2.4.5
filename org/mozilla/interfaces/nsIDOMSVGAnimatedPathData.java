//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDOMSVGAnimatedPathData extends nsISupports
{
    public static final String NS_IDOMSVGANIMATEDPATHDATA_IID = "{6ef2b400-dbf4-4c12-8787-fe15caac5648}";
    
    nsIDOMSVGPathSegList getPathSegList();
    
    nsIDOMSVGPathSegList getNormalizedPathSegList();
    
    nsIDOMSVGPathSegList getAnimatedPathSegList();
    
    nsIDOMSVGPathSegList getAnimatedNormalizedPathSegList();
}
