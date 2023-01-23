//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIControllerCommand extends nsISupports
{
    public static final String NS_ICONTROLLERCOMMAND_IID = "{0eae9a46-1dd2-11b2-aca0-9176f05fe9db}";
    
    boolean isCommandEnabled(final String p0, final nsISupports p1);
    
    void getCommandStateParams(final String p0, final nsICommandParams p1, final nsISupports p2);
    
    void doCommand(final String p0, final nsISupports p1);
    
    void doCommandParams(final String p0, final nsICommandParams p1, final nsISupports p2);
}
