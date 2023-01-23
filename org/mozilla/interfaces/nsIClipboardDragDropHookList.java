//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIClipboardDragDropHookList extends nsISupports
{
    public static final String NS_ICLIPBOARDDRAGDROPHOOKLIST_IID = "{876a2015-6b66-11d7-8f18-0003938a9d96}";
    
    void addClipboardDragDropHooks(final nsIClipboardDragDropHooks p0);
    
    void removeClipboardDragDropHooks(final nsIClipboardDragDropHooks p0);
    
    nsISimpleEnumerator getHookEnumerator();
}
