//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDOMSVGClipPathElement extends nsIDOMSVGElement
{
    public static final String NS_IDOMSVGCLIPPATHELEMENT_IID = "{0c3f45a4-e6d0-44e7-a2f8-d128ecf1db9b}";
    public static final int SVG_CPUNITS_UNKNOWN = 0;
    public static final int SVG_CPUNITS_OBJECTBOUNDINGBOX = 1;
    public static final int SVG_CPUNITS_USERSPACEONUSE = 2;
    
    nsIDOMSVGAnimatedEnumeration getClipPathUnits();
}
