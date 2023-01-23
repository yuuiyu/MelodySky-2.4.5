//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDOMXULDocument extends nsISupports
{
    public static final String NS_IDOMXULDOCUMENT_IID = "{e64bb081-13ba-430e-ab70-73a9f1d3de58}";
    
    nsIDOMNode getPopupNode();
    
    void setPopupNode(final nsIDOMNode p0);
    
    nsIDOMNode getTooltipNode();
    
    void setTooltipNode(final nsIDOMNode p0);
    
    nsIDOMXULCommandDispatcher getCommandDispatcher();
    
    int getWidth();
    
    int getHeight();
    
    nsIDOMNodeList getElementsByAttribute(final String p0, final String p1);
    
    void addBroadcastListenerFor(final nsIDOMElement p0, final nsIDOMElement p1, final String p2);
    
    void removeBroadcastListenerFor(final nsIDOMElement p0, final nsIDOMElement p1, final String p2);
    
    void persist(final String p0, final String p1);
    
    void loadOverlay(final String p0, final nsIObserver p1);
}
