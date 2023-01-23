//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsISecurityCheckedComponent extends nsISupports
{
    public static final String NS_ISECURITYCHECKEDCOMPONENT_IID = "{0dad9e8c-a12d-4dcb-9a6f-7d09839356e1}";
    
    String canCreateWrapper(final String p0);
    
    String canCallMethod(final String p0, final String p1);
    
    String canGetProperty(final String p0, final String p1);
    
    String canSetProperty(final String p0, final String p1);
}
