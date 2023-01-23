//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDOMSVGPathSegList extends nsISupports
{
    public static final String NS_IDOMSVGPATHSEGLIST_IID = "{94a6db98-3f34-4529-a35f-89ef49713795}";
    
    long getNumberOfItems();
    
    void clear();
    
    nsIDOMSVGPathSeg initialize(final nsIDOMSVGPathSeg p0);
    
    nsIDOMSVGPathSeg getItem(final long p0);
    
    nsIDOMSVGPathSeg insertItemBefore(final nsIDOMSVGPathSeg p0, final long p1);
    
    nsIDOMSVGPathSeg replaceItem(final nsIDOMSVGPathSeg p0, final long p1);
    
    nsIDOMSVGPathSeg removeItem(final long p0);
    
    nsIDOMSVGPathSeg appendItem(final nsIDOMSVGPathSeg p0);
}
