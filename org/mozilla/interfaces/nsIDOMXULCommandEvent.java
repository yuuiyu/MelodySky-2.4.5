//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDOMXULCommandEvent extends nsIDOMUIEvent
{
    public static final String NS_IDOMXULCOMMANDEVENT_IID = "{f9fa8205-a988-4828-9228-f3332d5475ac}";
    
    boolean getCtrlKey();
    
    boolean getShiftKey();
    
    boolean getAltKey();
    
    boolean getMetaKey();
    
    nsIDOMEvent getSourceEvent();
    
    void initCommandEvent(final String p0, final boolean p1, final boolean p2, final nsIDOMAbstractView p3, final int p4, final boolean p5, final boolean p6, final boolean p7, final boolean p8, final nsIDOMEvent p9);
}
