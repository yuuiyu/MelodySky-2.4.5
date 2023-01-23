//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIEditorDocShell extends nsISupports
{
    public static final String NS_IEDITORDOCSHELL_IID = "{3bdb8f01-f141-11d4-a73c-fba4aba8a3fc}";
    
    nsIEditor getEditor();
    
    void setEditor(final nsIEditor p0);
    
    boolean getEditable();
    
    boolean getHasEditingSession();
    
    void makeEditable(final boolean p0);
}
