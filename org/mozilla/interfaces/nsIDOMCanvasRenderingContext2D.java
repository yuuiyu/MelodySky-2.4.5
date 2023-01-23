//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDOMCanvasRenderingContext2D extends nsISupports
{
    public static final String NS_IDOMCANVASRENDERINGCONTEXT2D_IID = "{ab27f42d-e1e1-4ef6-9c83-059a81da479b}";
    
    nsIDOMHTMLCanvasElement getCanvas();
    
    void save();
    
    void restore();
    
    void scale(final float p0, final float p1);
    
    void rotate(final float p0);
    
    void translate(final float p0, final float p1);
    
    float getGlobalAlpha();
    
    void setGlobalAlpha(final float p0);
    
    String getGlobalCompositeOperation();
    
    void setGlobalCompositeOperation(final String p0);
    
    nsIVariant getStrokeStyle();
    
    void setStrokeStyle(final nsIVariant p0);
    
    nsIVariant getFillStyle();
    
    void setFillStyle(final nsIVariant p0);
    
    nsIDOMCanvasGradient createLinearGradient(final float p0, final float p1, final float p2, final float p3);
    
    nsIDOMCanvasGradient createRadialGradient(final float p0, final float p1, final float p2, final float p3, final float p4, final float p5);
    
    nsIDOMCanvasPattern createPattern(final nsIDOMHTMLElement p0, final String p1);
    
    float getLineWidth();
    
    void setLineWidth(final float p0);
    
    String getLineCap();
    
    void setLineCap(final String p0);
    
    String getLineJoin();
    
    void setLineJoin(final String p0);
    
    float getMiterLimit();
    
    void setMiterLimit(final float p0);
    
    float getShadowOffsetX();
    
    void setShadowOffsetX(final float p0);
    
    float getShadowOffsetY();
    
    void setShadowOffsetY(final float p0);
    
    float getShadowBlur();
    
    void setShadowBlur(final float p0);
    
    String getShadowColor();
    
    void setShadowColor(final String p0);
    
    void clearRect(final float p0, final float p1, final float p2, final float p3);
    
    void fillRect(final float p0, final float p1, final float p2, final float p3);
    
    void strokeRect(final float p0, final float p1, final float p2, final float p3);
    
    void beginPath();
    
    void closePath();
    
    void moveTo(final float p0, final float p1);
    
    void lineTo(final float p0, final float p1);
    
    void quadraticCurveTo(final float p0, final float p1, final float p2, final float p3);
    
    void bezierCurveTo(final float p0, final float p1, final float p2, final float p3, final float p4, final float p5);
    
    void arcTo(final float p0, final float p1, final float p2, final float p3, final float p4);
    
    void arc(final float p0, final float p1, final float p2, final float p3, final float p4, final boolean p5);
    
    void rect(final float p0, final float p1, final float p2, final float p3);
    
    void fill();
    
    void stroke();
    
    void clip();
    
    void drawImage();
    
    boolean isPointInPath(final float p0, final float p1);
    
    void getImageData();
    
    void putImageData();
    
    void drawWindow(final nsIDOMWindow p0, final int p1, final int p2, final int p3, final int p4, final String p5);
}
