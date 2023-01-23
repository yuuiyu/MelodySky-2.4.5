//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIControllerCommandTable extends nsISupports
{
    public static final String NS_ICONTROLLERCOMMANDTABLE_IID = "{d1a47834-6ad4-11d7-bfad-000393636592}";
    
    void makeImmutable();
    
    void registerCommand(final String p0, final nsIControllerCommand p1);
    
    void unregisterCommand(final String p0, final nsIControllerCommand p1);
    
    nsIControllerCommand findCommandHandler(final String p0);
    
    boolean isCommandEnabled(final String p0, final nsISupports p1);
    
    void updateCommandState(final String p0, final nsISupports p1);
    
    boolean supportsCommand(final String p0, final nsISupports p1);
    
    void doCommand(final String p0, final nsISupports p1);
    
    void doCommandParams(final String p0, final nsICommandParams p1, final nsISupports p2);
    
    void getCommandState(final String p0, final nsICommandParams p1, final nsISupports p2);
}
