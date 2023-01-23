//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDOMSVGTransform extends nsISupports
{
    public static final String NS_IDOMSVGTRANSFORM_IID = "{29cc2e14-6d18-4710-bda9-a88d9d3bc8dc}";
    public static final int SVG_TRANSFORM_UNKNOWN = 0;
    public static final int SVG_TRANSFORM_MATRIX = 1;
    public static final int SVG_TRANSFORM_TRANSLATE = 2;
    public static final int SVG_TRANSFORM_SCALE = 3;
    public static final int SVG_TRANSFORM_ROTATE = 4;
    public static final int SVG_TRANSFORM_SKEWX = 5;
    public static final int SVG_TRANSFORM_SKEWY = 6;
    
    int getType();
    
    nsIDOMSVGMatrix getMatrix();
    
    float getAngle();
    
    void setMatrix(final nsIDOMSVGMatrix p0);
    
    void setTranslate(final float p0, final float p1);
    
    void setScale(final float p0, final float p1);
    
    void setRotate(final float p0, final float p1, final float p2);
    
    void setSkewX(final float p0);
    
    void setSkewY(final float p0);
}
