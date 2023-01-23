//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDOMSVGNumberList extends nsISupports
{
    public static final String NS_IDOMSVGNUMBERLIST_IID = "{59364ec4-faf1-460f-bf58-e6a6a2769a3a}";
    
    long getNumberOfItems();
    
    void clear();
    
    nsIDOMSVGNumber initialize(final nsIDOMSVGNumber p0);
    
    nsIDOMSVGNumber getItem(final long p0);
    
    nsIDOMSVGNumber insertItemBefore(final nsIDOMSVGNumber p0, final long p1);
    
    nsIDOMSVGNumber replaceItem(final nsIDOMSVGNumber p0, final long p1);
    
    nsIDOMSVGNumber removeItem(final long p0);
    
    nsIDOMSVGNumber appendItem(final nsIDOMSVGNumber p0);
}
