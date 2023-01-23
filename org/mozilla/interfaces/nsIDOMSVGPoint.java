//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDOMSVGPoint extends nsISupports
{
    public static final String NS_IDOMSVGPOINT_IID = "{45f18f8f-1315-4447-a7d5-8aeca77bdcaf}";
    
    float getX();
    
    void setX(final float p0);
    
    float getY();
    
    void setY(final float p0);
    
    nsIDOMSVGPoint matrixTransform(final nsIDOMSVGMatrix p0);
}
