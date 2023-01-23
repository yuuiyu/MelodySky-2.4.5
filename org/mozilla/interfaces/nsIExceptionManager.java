//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIExceptionManager extends nsISupports
{
    public static final String NS_IEXCEPTIONMANAGER_IID = "{efc9d00b-231c-4feb-852c-ac017266a415}";
    
    void setCurrentException(final nsIException p0);
    
    nsIException getCurrentException();
    
    nsIException getExceptionFromProvider(final long p0, final nsIException p1);
}
