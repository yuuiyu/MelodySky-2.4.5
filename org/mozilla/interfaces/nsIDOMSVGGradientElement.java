//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDOMSVGGradientElement extends nsIDOMSVGElement
{
    public static final String NS_IDOMSVGGRADIENTELEMENT_IID = "{988b2de4-137b-4bb8-a15e-fe94038e9cf3}";
    public static final int SVG_SPREADMETHOD_UNKNOWN = 0;
    public static final int SVG_SPREADMETHOD_PAD = 1;
    public static final int SVG_SPREADMETHOD_REFLECT = 2;
    public static final int SVG_SPREADMETHOD_REPEAT = 3;
    public static final int SVG_GRUNITS_UNKNOWN = 0;
    public static final int SVG_GRUNITS_OBJECTBOUNDINGBOX = 1;
    public static final int SVG_GRUNITS_USERSPACEONUSE = 2;
    
    nsIDOMSVGAnimatedEnumeration getGradientUnits();
    
    nsIDOMSVGAnimatedTransformList getGradientTransform();
    
    nsIDOMSVGAnimatedEnumeration getSpreadMethod();
}
