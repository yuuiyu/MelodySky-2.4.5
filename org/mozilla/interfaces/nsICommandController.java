//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsICommandController extends nsISupports
{
    public static final String NS_ICOMMANDCONTROLLER_IID = "{ebe55080-c8a9-11d5-a73c-dd620d6e04bc}";
    
    void getCommandStateWithParams(final String p0, final nsICommandParams p1);
    
    void doCommandWithParams(final String p0, final nsICommandParams p1);
}
