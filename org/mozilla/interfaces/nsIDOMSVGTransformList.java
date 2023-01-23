//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDOMSVGTransformList extends nsISupports
{
    public static final String NS_IDOMSVGTRANSFORMLIST_IID = "{df41474c-a4f8-4ec3-ae79-4342e6f56d8e}";
    
    long getNumberOfItems();
    
    void clear();
    
    nsIDOMSVGTransform initialize(final nsIDOMSVGTransform p0);
    
    nsIDOMSVGTransform getItem(final long p0);
    
    nsIDOMSVGTransform insertItemBefore(final nsIDOMSVGTransform p0, final long p1);
    
    nsIDOMSVGTransform replaceItem(final nsIDOMSVGTransform p0, final long p1);
    
    nsIDOMSVGTransform removeItem(final long p0);
    
    nsIDOMSVGTransform appendItem(final nsIDOMSVGTransform p0);
    
    nsIDOMSVGTransform createSVGTransformFromMatrix(final nsIDOMSVGMatrix p0);
    
    nsIDOMSVGTransform consolidate();
    
    nsIDOMSVGMatrix getConsolidationMatrix();
}
