//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsITransaction extends nsISupports
{
    public static final String NS_ITRANSACTION_IID = "{58e330c1-7b48-11d2-98b9-00805f297d89}";
    
    void doTransaction();
    
    void undoTransaction();
    
    void redoTransaction();
    
    boolean getIsTransient();
    
    boolean merge(final nsITransaction p0);
}
