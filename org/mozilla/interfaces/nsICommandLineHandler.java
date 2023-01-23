//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsICommandLineHandler extends nsISupports
{
    public static final String NS_ICOMMANDLINEHANDLER_IID = "{d4b123df-51ee-48b1-a663-002180e60d3b}";
    
    void handle(final nsICommandLine p0);
    
    String getHelpInfo();
}
