//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDOMSVGException extends nsISupports
{
    public static final String NS_IDOMSVGEXCEPTION_IID = "{64e6f0e1-af99-4bb9-ab25-7e56012f0021}";
    public static final int SVG_WRONG_TYPE_ERR = 0;
    public static final int SVG_INVALID_VALUE_ERR = 1;
    public static final int SVG_MATRIX_NOT_INVERTABLE = 2;
    
    int getCode();
}
