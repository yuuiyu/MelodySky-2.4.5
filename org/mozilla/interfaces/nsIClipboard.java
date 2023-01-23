//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIClipboard extends nsISupports
{
    public static final String NS_ICLIPBOARD_IID = "{8b5314ba-db01-11d2-96ce-0060b0fb9956}";
    public static final int kSelectionClipboard = 0;
    public static final int kGlobalClipboard = 1;
    
    void setData(final nsITransferable p0, final nsIClipboardOwner p1, final int p2);
    
    void getData(final nsITransferable p0, final int p1);
    
    void emptyClipboard(final int p0);
    
    boolean hasDataMatchingFlavors(final nsISupportsArray p0, final int p1);
    
    boolean supportsSelectionClipboard();
}
