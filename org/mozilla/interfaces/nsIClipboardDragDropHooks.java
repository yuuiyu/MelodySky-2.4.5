//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIClipboardDragDropHooks extends nsISupports
{
    public static final String NS_ICLIPBOARDDRAGDROPHOOKS_IID = "{e03e6c5e-0d84-4c0b-8739-e6b8d51922de}";
    
    boolean allowStartDrag(final nsIDOMEvent p0);
    
    boolean allowDrop(final nsIDOMEvent p0, final nsIDragSession p1);
    
    boolean onCopyOrDrag(final nsIDOMEvent p0, final nsITransferable p1);
    
    boolean onPasteOrDrop(final nsIDOMEvent p0, final nsITransferable p1);
}
