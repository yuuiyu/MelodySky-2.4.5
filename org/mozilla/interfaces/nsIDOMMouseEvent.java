//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDOMMouseEvent extends nsIDOMUIEvent
{
    public static final String NS_IDOMMOUSEEVENT_IID = "{ff751edc-8b02-aae7-0010-8301838a3123}";
    
    int getScreenX();
    
    int getScreenY();
    
    int getClientX();
    
    int getClientY();
    
    boolean getCtrlKey();
    
    boolean getShiftKey();
    
    boolean getAltKey();
    
    boolean getMetaKey();
    
    int getButton();
    
    nsIDOMEventTarget getRelatedTarget();
    
    void initMouseEvent(final String p0, final boolean p1, final boolean p2, final nsIDOMAbstractView p3, final int p4, final int p5, final int p6, final int p7, final int p8, final boolean p9, final boolean p10, final boolean p11, final boolean p12, final int p13, final nsIDOMEventTarget p14);
}
