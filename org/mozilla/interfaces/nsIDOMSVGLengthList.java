//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDOMSVGLengthList extends nsISupports
{
    public static final String NS_IDOMSVGLENGTHLIST_IID = "{a8760fcd-3de5-446a-a009-5cf877e7a4df}";
    
    long getNumberOfItems();
    
    void clear();
    
    nsIDOMSVGLength initialize(final nsIDOMSVGLength p0);
    
    nsIDOMSVGLength getItem(final long p0);
    
    nsIDOMSVGLength insertItemBefore(final nsIDOMSVGLength p0, final long p1);
    
    nsIDOMSVGLength replaceItem(final nsIDOMSVGLength p0, final long p1);
    
    nsIDOMSVGLength removeItem(final long p0);
    
    nsIDOMSVGLength appendItem(final nsIDOMSVGLength p0);
}
