//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDOMSVGViewSpec extends nsIDOMSVGZoomAndPan
{
    public static final String NS_IDOMSVGVIEWSPEC_IID = "{ede34b03-57b6-45bf-a259-3550b5697286}";
    
    nsIDOMSVGTransformList getTransform();
    
    nsIDOMSVGElement getViewTarget();
    
    String getViewBoxString();
    
    String getPreserveAspectRatioString();
    
    String getTransformString();
    
    String getViewTargetString();
}
