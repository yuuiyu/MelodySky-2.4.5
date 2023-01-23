//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsITreeColumns extends nsISupports
{
    public static final String NS_ITREECOLUMNS_IID = "{fcc7b6b5-f7d7-4e57-abd1-080602deb21d}";
    
    nsITreeBoxObject getTree();
    
    int getCount();
    
    nsITreeColumn getFirstColumn();
    
    nsITreeColumn getLastColumn();
    
    nsITreeColumn getPrimaryColumn();
    
    nsITreeColumn getSortedColumn();
    
    nsITreeColumn getKeyColumn();
    
    nsITreeColumn getColumnFor(final nsIDOMElement p0);
    
    nsITreeColumn getNamedColumn(final String p0);
    
    nsITreeColumn getColumnAt(final int p0);
    
    void invalidateColumns();
    
    void restoreNaturalOrder();
}
