//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsISAXLocator extends nsISupports
{
    public static final String NS_ISAXLOCATOR_IID = "{7a307c6c-6cc9-11da-be43-001422106990}";
    
    int getColumnNumber();
    
    int getLineNumber();
    
    String getPublicId();
    
    String getSystemId();
}
