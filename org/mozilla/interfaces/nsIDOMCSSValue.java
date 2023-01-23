//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDOMCSSValue extends nsISupports
{
    public static final String NS_IDOMCSSVALUE_IID = "{009f7ea5-9e80-41be-b008-db62f10823f2}";
    public static final int CSS_INHERIT = 0;
    public static final int CSS_PRIMITIVE_VALUE = 1;
    public static final int CSS_VALUE_LIST = 2;
    public static final int CSS_CUSTOM = 3;
    
    String getCssText();
    
    void setCssText(final String p0);
    
    int getCssValueType();
}
