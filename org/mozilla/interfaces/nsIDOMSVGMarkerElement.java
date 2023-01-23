//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDOMSVGMarkerElement extends nsIDOMSVGElement
{
    public static final String NS_IDOMSVGMARKERELEMENT_IID = "{7d89ceb8-f985-4095-8f24-421910704e5e}";
    public static final int SVG_MARKERUNITS_UNKNOWN = 0;
    public static final int SVG_MARKERUNITS_USERSPACEONUSE = 1;
    public static final int SVG_MARKERUNITS_STROKEWIDTH = 2;
    public static final int SVG_MARKER_ORIENT_UNKNOWN = 0;
    public static final int SVG_MARKER_ORIENT_AUTO = 1;
    public static final int SVG_MARKER_ORIENT_ANGLE = 2;
    
    nsIDOMSVGAnimatedLength getRefX();
    
    nsIDOMSVGAnimatedLength getRefY();
    
    nsIDOMSVGAnimatedEnumeration getMarkerUnits();
    
    nsIDOMSVGAnimatedLength getMarkerWidth();
    
    nsIDOMSVGAnimatedLength getMarkerHeight();
    
    nsIDOMSVGAnimatedEnumeration getOrientType();
    
    nsIDOMSVGAnimatedAngle getOrientAngle();
    
    void setOrientToAuto();
    
    void setOrientToAngle(final nsIDOMSVGAngle p0);
}
