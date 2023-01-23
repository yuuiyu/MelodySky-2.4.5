//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsISAXErrorHandler extends nsISupports
{
    public static final String NS_ISAXERRORHANDLER_IID = "{e02b6693-6cca-11da-be43-001422106990}";
    
    void error(final nsISAXLocator p0, final String p1);
    
    void fatalError(final nsISAXLocator p0, final String p1);
    
    void ignorableWarning(final nsISAXLocator p0, final String p1);
}
