//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIExceptionService extends nsIExceptionManager
{
    public static final String NS_IEXCEPTIONSERVICE_IID = "{35a88f54-f267-4414-92a7-191f6454ab52}";
    
    nsIExceptionManager getCurrentExceptionManager();
    
    void registerExceptionProvider(final nsIExceptionProvider p0, final long p1);
    
    void unregisterExceptionProvider(final nsIExceptionProvider p0, final long p1);
}
