//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDOMSVGPathElement extends nsIDOMSVGElement
{
    public static final String NS_IDOMSVGPATHELEMENT_IID = "{2b19e692-3338-440f-a998-3cb1e8474999}";
    
    nsIDOMSVGAnimatedNumber getPathLength();
    
    float getTotalLength();
    
    nsIDOMSVGPoint getPointAtLength(final float p0);
    
    long getPathSegAtLength(final float p0);
    
    nsIDOMSVGPathSegClosePath createSVGPathSegClosePath();
    
    nsIDOMSVGPathSegMovetoAbs createSVGPathSegMovetoAbs(final float p0, final float p1);
    
    nsIDOMSVGPathSegMovetoRel createSVGPathSegMovetoRel(final float p0, final float p1);
    
    nsIDOMSVGPathSegLinetoAbs createSVGPathSegLinetoAbs(final float p0, final float p1);
    
    nsIDOMSVGPathSegLinetoRel createSVGPathSegLinetoRel(final float p0, final float p1);
    
    nsIDOMSVGPathSegCurvetoCubicAbs createSVGPathSegCurvetoCubicAbs(final float p0, final float p1, final float p2, final float p3, final float p4, final float p5);
    
    nsIDOMSVGPathSegCurvetoCubicRel createSVGPathSegCurvetoCubicRel(final float p0, final float p1, final float p2, final float p3, final float p4, final float p5);
    
    nsIDOMSVGPathSegCurvetoQuadraticAbs createSVGPathSegCurvetoQuadraticAbs(final float p0, final float p1, final float p2, final float p3);
    
    nsIDOMSVGPathSegCurvetoQuadraticRel createSVGPathSegCurvetoQuadraticRel(final float p0, final float p1, final float p2, final float p3);
    
    nsIDOMSVGPathSegArcAbs createSVGPathSegArcAbs(final float p0, final float p1, final float p2, final float p3, final float p4, final boolean p5, final boolean p6);
    
    nsIDOMSVGPathSegArcRel createSVGPathSegArcRel(final float p0, final float p1, final float p2, final float p3, final float p4, final boolean p5, final boolean p6);
    
    nsIDOMSVGPathSegLinetoHorizontalAbs createSVGPathSegLinetoHorizontalAbs(final float p0);
    
    nsIDOMSVGPathSegLinetoHorizontalRel createSVGPathSegLinetoHorizontalRel(final float p0);
    
    nsIDOMSVGPathSegLinetoVerticalAbs createSVGPathSegLinetoVerticalAbs(final float p0);
    
    nsIDOMSVGPathSegLinetoVerticalRel createSVGPathSegLinetoVerticalRel(final float p0);
    
    nsIDOMSVGPathSegCurvetoCubicSmoothAbs createSVGPathSegCurvetoCubicSmoothAbs(final float p0, final float p1, final float p2, final float p3);
    
    nsIDOMSVGPathSegCurvetoCubicSmoothRel createSVGPathSegCurvetoCubicSmoothRel(final float p0, final float p1, final float p2, final float p3);
    
    nsIDOMSVGPathSegCurvetoQuadraticSmoothAbs createSVGPathSegCurvetoQuadraticSmoothAbs(final float p0, final float p1);
    
    nsIDOMSVGPathSegCurvetoQuadraticSmoothRel createSVGPathSegCurvetoQuadraticSmoothRel(final float p0, final float p1);
}
