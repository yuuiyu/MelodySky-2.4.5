//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIClipboardCommands extends nsISupports
{
    public static final String NS_ICLIPBOARDCOMMANDS_IID = "{b8100c90-73be-11d2-92a5-00105a1b0d64}";
    
    boolean canCutSelection();
    
    boolean canCopySelection();
    
    boolean canCopyLinkLocation();
    
    boolean canCopyImageLocation();
    
    boolean canCopyImageContents();
    
    boolean canPaste();
    
    void cutSelection();
    
    void copySelection();
    
    void copyLinkLocation();
    
    void copyImageLocation();
    
    void copyImageContents();
    
    void paste();
    
    void selectAll();
    
    void selectNone();
}
