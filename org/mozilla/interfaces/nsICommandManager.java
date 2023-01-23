//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsICommandManager extends nsISupports
{
    public static final String NS_ICOMMANDMANAGER_IID = "{080d2001-f91e-11d4-a73c-f9242928207c}";
    
    void addCommandObserver(final nsIObserver p0, final String p1);
    
    void removeCommandObserver(final nsIObserver p0, final String p1);
    
    boolean isCommandSupported(final String p0, final nsIDOMWindow p1);
    
    boolean isCommandEnabled(final String p0, final nsIDOMWindow p1);
    
    void getCommandState(final String p0, final nsIDOMWindow p1, final nsICommandParams p2);
    
    void doCommand(final String p0, final nsICommandParams p1, final nsIDOMWindow p2);
}
