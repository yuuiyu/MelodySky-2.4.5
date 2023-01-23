//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDragSession extends nsISupports
{
    public static final String NS_IDRAGSESSION_IID = "{cba22c53-fcce-11d2-96d4-0060b0fb9956}";
    
    boolean getCanDrop();
    
    void setCanDrop(final boolean p0);
    
    long getDragAction();
    
    void setDragAction(final long p0);
    
    long getNumDropItems();
    
    nsIDOMDocument getSourceDocument();
    
    nsIDOMNode getSourceNode();
    
    void getData(final nsITransferable p0, final long p1);
    
    boolean isDataFlavorSupported(final String p0);
}
