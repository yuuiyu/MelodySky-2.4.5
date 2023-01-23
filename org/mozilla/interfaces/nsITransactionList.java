//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsITransactionList extends nsISupports
{
    public static final String NS_ITRANSACTIONLIST_IID = "{97f863f3-f886-11d4-9d39-0060b0f8baff}";
    
    int getNumItems();
    
    boolean itemIsBatch(final int p0);
    
    nsITransaction getItem(final int p0);
    
    int getNumChildrenForItem(final int p0);
    
    nsITransactionList getChildListForItem(final int p0);
}
