//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDOMNSUIEvent extends nsISupports
{
    public static final String NS_IDOMNSUIEVENT_IID = "{a6cf90c4-15b3-11d2-932e-00805f8add32}";
    public static final int SCROLL_PAGE_UP = -32768;
    public static final int SCROLL_PAGE_DOWN = 32768;
    
    boolean getPreventDefault();
    
    int getLayerX();
    
    int getLayerY();
    
    int getPageX();
    
    int getPageY();
    
    long getWhich();
    
    nsIDOMNode getRangeParent();
    
    int getRangeOffset();
    
    boolean getCancelBubble();
    
    void setCancelBubble(final boolean p0);
    
    boolean getIsChar();
}
