//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIHTMLObjectResizer extends nsISupports
{
    public static final String NS_IHTMLOBJECTRESIZER_IID = "{b0338f6c-ded3-4c39-a953-56e8bae494f5}";
    public static final short eTopLeft = 0;
    public static final short eTop = 1;
    public static final short eTopRight = 2;
    public static final short eLeft = 3;
    public static final short eRight = 4;
    public static final short eBottomLeft = 5;
    public static final short eBottom = 6;
    public static final short eBottomRight = 7;
    
    nsIDOMElement getResizedObject();
    
    boolean getObjectResizingEnabled();
    
    void setObjectResizingEnabled(final boolean p0);
    
    void showResizers(final nsIDOMElement p0);
    
    void hideResizers();
    
    void refreshResizers();
    
    void mouseDown(final int p0, final int p1, final nsIDOMElement p2);
    
    void mouseUp(final int p0, final int p1, final nsIDOMElement p2);
    
    void mouseMove(final nsIDOMEvent p0);
    
    void addObjectResizeEventListener(final nsIHTMLObjectResizeListener p0);
    
    void removeObjectResizeEventListener(final nsIHTMLObjectResizeListener p0);
}
