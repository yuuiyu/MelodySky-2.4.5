//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDocumentStateListener extends nsISupports
{
    public static final String NS_IDOCUMENTSTATELISTENER_IID = "{050cdc00-3b8e-11d3-9ce4-a458f454fcbc}";
    
    void notifyDocumentCreated();
    
    void notifyDocumentWillBeDestroyed();
    
    void notifyDocumentStateChanged(final boolean p0);
}
