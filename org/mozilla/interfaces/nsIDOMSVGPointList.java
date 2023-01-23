//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDOMSVGPointList extends nsISupports
{
    public static final String NS_IDOMSVGPOINTLIST_IID = "{4c12af24-0fc2-4fe7-b71d-5d6b41d463c1}";
    
    long getNumberOfItems();
    
    void clear();
    
    nsIDOMSVGPoint initialize(final nsIDOMSVGPoint p0);
    
    nsIDOMSVGPoint getItem(final long p0);
    
    nsIDOMSVGPoint insertItemBefore(final nsIDOMSVGPoint p0, final long p1);
    
    nsIDOMSVGPoint replaceItem(final nsIDOMSVGPoint p0, final long p1);
    
    nsIDOMSVGPoint removeItem(final long p0);
    
    nsIDOMSVGPoint appendItem(final nsIDOMSVGPoint p0);
}
