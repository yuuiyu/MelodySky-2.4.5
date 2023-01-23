//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDOMSVGMatrix extends nsISupports
{
    public static final String NS_IDOMSVGMATRIX_IID = "{ec2da3ef-5a99-49ed-aaef-b5af916c14ac}";
    
    float getA();
    
    void setA(final float p0);
    
    float getB();
    
    void setB(final float p0);
    
    float getC();
    
    void setC(final float p0);
    
    float getD();
    
    void setD(final float p0);
    
    float getE();
    
    void setE(final float p0);
    
    float getF();
    
    void setF(final float p0);
    
    nsIDOMSVGMatrix multiply(final nsIDOMSVGMatrix p0);
    
    nsIDOMSVGMatrix inverse();
    
    nsIDOMSVGMatrix translate(final float p0, final float p1);
    
    nsIDOMSVGMatrix scale(final float p0);
    
    nsIDOMSVGMatrix scaleNonUniform(final float p0, final float p1);
    
    nsIDOMSVGMatrix rotate(final float p0);
    
    nsIDOMSVGMatrix rotateFromVector(final float p0, final float p1);
    
    nsIDOMSVGMatrix flipX();
    
    nsIDOMSVGMatrix flipY();
    
    nsIDOMSVGMatrix skewX(final float p0);
    
    nsIDOMSVGMatrix skewY(final float p0);
}
