//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIBoxObject extends nsISupports
{
    public static final String NS_IBOXOBJECT_IID = "{caabf76f-9d35-401f-beac-3955817c645c}";
    
    nsIDOMElement getElement();
    
    nsIBoxLayoutManager getLayoutManager();
    
    void setLayoutManager(final nsIBoxLayoutManager p0);
    
    nsIBoxPaintManager getPaintManager();
    
    void setPaintManager(final nsIBoxPaintManager p0);
    
    int getX();
    
    int getY();
    
    int getScreenX();
    
    int getScreenY();
    
    int getWidth();
    
    int getHeight();
    
    nsISupports getPropertyAsSupports(final String p0);
    
    void setPropertyAsSupports(final String p0, final nsISupports p1);
    
    String getProperty(final String p0);
    
    void setProperty(final String p0, final String p1);
    
    void removeProperty(final String p0);
    
    nsIDOMElement getParentBox();
    
    nsIDOMElement getFirstChild();
    
    nsIDOMElement getLastChild();
    
    nsIDOMElement getNextSibling();
    
    nsIDOMElement getPreviousSibling();
    
    String getLookAndFeelMetric(final String p0);
}
