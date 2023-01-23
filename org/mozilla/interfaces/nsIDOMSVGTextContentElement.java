//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDOMSVGTextContentElement extends nsIDOMSVGElement
{
    public static final String NS_IDOMSVGTEXTCONTENTELEMENT_IID = "{87ad94bc-07c9-412b-b2d8-de245a2e84a5}";
    public static final int LENGTHADJUST_UNKNOWN = 0;
    public static final int LENGTHADJUST_SPACING = 1;
    public static final int LENGTHADJUST_SPACINGANDGLYPHS = 2;
    
    nsIDOMSVGAnimatedLength getTextLength();
    
    nsIDOMSVGAnimatedEnumeration getLengthAdjust();
    
    int getNumberOfChars();
    
    float getComputedTextLength();
    
    float getSubStringLength(final long p0, final long p1);
    
    nsIDOMSVGPoint getStartPositionOfChar(final long p0);
    
    nsIDOMSVGPoint getEndPositionOfChar(final long p0);
    
    nsIDOMSVGRect getExtentOfChar(final long p0);
    
    float getRotationOfChar(final long p0);
    
    int getCharNumAtPosition(final nsIDOMSVGPoint p0);
    
    void selectSubString(final long p0, final long p1);
}
