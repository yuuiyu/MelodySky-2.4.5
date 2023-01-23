//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIWritablePropertyBag extends nsIPropertyBag
{
    public static final String NS_IWRITABLEPROPERTYBAG_IID = "{96fc4671-eeb4-4823-9421-e50fb70ad353}";
    
    void setProperty(final String p0, final nsIVariant p1);
    
    void deleteProperty(final String p0);
}
