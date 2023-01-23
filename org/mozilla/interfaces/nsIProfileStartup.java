//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIProfileStartup extends nsISupports
{
    public static final String NS_IPROFILESTARTUP_IID = "{048e5ca1-0eb7-4bb1-a9a2-a36f7d4e0e3c}";
    
    nsIFile getDirectory();
    
    void doStartup();
}
