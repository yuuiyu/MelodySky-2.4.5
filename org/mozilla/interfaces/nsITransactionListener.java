//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsITransactionListener extends nsISupports
{
    public static final String NS_ITRANSACTIONLISTENER_IID = "{58e330c4-7b48-11d2-98b9-00805f297d89}";
    
    boolean willDo(final nsITransactionManager p0, final nsITransaction p1);
    
    void didDo(final nsITransactionManager p0, final nsITransaction p1, final long p2);
    
    boolean willUndo(final nsITransactionManager p0, final nsITransaction p1);
    
    void didUndo(final nsITransactionManager p0, final nsITransaction p1, final long p2);
    
    boolean willRedo(final nsITransactionManager p0, final nsITransaction p1);
    
    void didRedo(final nsITransactionManager p0, final nsITransaction p1, final long p2);
    
    boolean willBeginBatch(final nsITransactionManager p0);
    
    void didBeginBatch(final nsITransactionManager p0, final long p1);
    
    boolean willEndBatch(final nsITransactionManager p0);
    
    void didEndBatch(final nsITransactionManager p0, final long p1);
    
    boolean willMerge(final nsITransactionManager p0, final nsITransaction p1, final nsITransaction p2);
    
    void didMerge(final nsITransactionManager p0, final nsITransaction p1, final nsITransaction p2, final boolean p3, final long p4);
}
