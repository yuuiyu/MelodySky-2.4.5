//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIMozAxPlugin extends nsISupports
{
    public static final String NS_IMOZAXPLUGIN_IID = "{b30c2717-2bbf-4475-9ddf-9e26f893f32a}";
    
    void invoke(final String p0);
    
    void invoke1(final String p0, final nsIVariant p1);
    
    void invoke2(final String p0, final nsIVariant p1, final nsIVariant p2);
    
    void invoke3(final String p0, final nsIVariant p1, final nsIVariant p2, final nsIVariant p3);
    
    void invoke4(final String p0, final nsIVariant p1, final nsIVariant p2, final nsIVariant p3, final nsIVariant p4);
    
    nsIVariant getProperty(final String p0);
    
    void setProperty(final String p0, final nsIVariant p1);
}
