//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIHTMLAbsPosEditor extends nsISupports
{
    public static final String NS_IHTMLABSPOSEDITOR_IID = "{91375f52-20e6-4757-9835-eb04fabe5498}";
    
    boolean getSelectionContainerAbsolutelyPositioned();
    
    nsIDOMElement getPositionedElement();
    
    boolean getAbsolutePositioningEnabled();
    
    void setAbsolutePositioningEnabled(final boolean p0);
    
    boolean getSnapToGridEnabled();
    
    void setSnapToGridEnabled(final boolean p0);
    
    long getGridSize();
    
    void setGridSize(final long p0);
    
    nsIDOMElement getAbsolutelyPositionedSelectionContainer();
    
    void absolutePositionSelection(final boolean p0);
    
    void relativeChangeZIndex(final int p0);
    
    void absolutelyPositionElement(final nsIDOMElement p0, final boolean p1);
    
    void setElementPosition(final nsIDOMElement p0, final int p1, final int p2);
    
    int getElementZIndex(final nsIDOMElement p0);
    
    void setElementZIndex(final nsIDOMElement p0, final int p1);
    
    int relativeChangeElementZIndex(final nsIDOMElement p0, final int p1);
    
    void showGrabberOnElement(final nsIDOMElement p0);
    
    void hideGrabber();
    
    void refreshGrabber();
}
