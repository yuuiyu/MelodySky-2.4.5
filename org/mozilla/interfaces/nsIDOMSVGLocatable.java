//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDOMSVGLocatable extends nsISupports
{
    public static final String NS_IDOMSVGLOCATABLE_IID = "{9cf4fc9c-90b2-4d66-88f5-35049b558aee}";
    
    nsIDOMSVGElement getNearestViewportElement();
    
    nsIDOMSVGElement getFarthestViewportElement();
    
    nsIDOMSVGRect getBBox();
    
    nsIDOMSVGMatrix getCTM();
    
    nsIDOMSVGMatrix getScreenCTM();
    
    nsIDOMSVGMatrix getTransformToElement(final nsIDOMSVGElement p0);
}
