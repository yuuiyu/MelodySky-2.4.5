//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDOMSVGSVGElement extends nsIDOMSVGElement
{
    public static final String NS_IDOMSVGSVGELEMENT_IID = "{67b8f41e-3577-4c8a-b1de-bef51186fe08}";
    
    nsIDOMSVGAnimatedLength getX();
    
    nsIDOMSVGAnimatedLength getY();
    
    nsIDOMSVGAnimatedLength getWidth();
    
    nsIDOMSVGAnimatedLength getHeight();
    
    String getContentScriptType();
    
    void setContentScriptType(final String p0);
    
    String getContentStyleType();
    
    void setContentStyleType(final String p0);
    
    nsIDOMSVGRect getViewport();
    
    float getPixelUnitToMillimeterX();
    
    float getPixelUnitToMillimeterY();
    
    float getScreenPixelToMillimeterX();
    
    float getScreenPixelToMillimeterY();
    
    boolean getUseCurrentView();
    
    void setUseCurrentView(final boolean p0);
    
    nsIDOMSVGViewSpec getCurrentView();
    
    float getCurrentScale();
    
    void setCurrentScale(final float p0);
    
    nsIDOMSVGPoint getCurrentTranslate();
    
    long suspendRedraw(final long p0);
    
    void unsuspendRedraw(final long p0);
    
    void unsuspendRedrawAll();
    
    void forceRedraw();
    
    void pauseAnimations();
    
    void unpauseAnimations();
    
    boolean animationsPaused();
    
    float getCurrentTime();
    
    void setCurrentTime(final float p0);
    
    nsIDOMNodeList getIntersectionList(final nsIDOMSVGRect p0, final nsIDOMSVGElement p1);
    
    nsIDOMNodeList getEnclosureList(final nsIDOMSVGRect p0, final nsIDOMSVGElement p1);
    
    boolean checkIntersection(final nsIDOMSVGElement p0, final nsIDOMSVGRect p1);
    
    boolean checkEnclosure(final nsIDOMSVGElement p0, final nsIDOMSVGRect p1);
    
    void deSelectAll();
    
    nsIDOMSVGNumber createSVGNumber();
    
    nsIDOMSVGLength createSVGLength();
    
    nsIDOMSVGAngle createSVGAngle();
    
    nsIDOMSVGPoint createSVGPoint();
    
    nsIDOMSVGMatrix createSVGMatrix();
    
    nsIDOMSVGRect createSVGRect();
    
    nsIDOMSVGTransform createSVGTransform();
    
    nsIDOMSVGTransform createSVGTransformFromMatrix(final nsIDOMSVGMatrix p0);
    
    String createSVGString();
    
    nsIDOMElement getElementById(final String p0);
    
    nsIDOMSVGMatrix getViewboxToViewportTransform();
}
