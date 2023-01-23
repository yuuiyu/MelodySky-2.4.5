//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDOMUIEvent extends nsIDOMEvent
{
    public static final String NS_IDOMUIEVENT_IID = "{a6cf90c3-15b3-11d2-932e-00805f8add32}";
    
    nsIDOMAbstractView getView();
    
    int getDetail();
    
    void initUIEvent(final String p0, final boolean p1, final boolean p2, final nsIDOMAbstractView p3, final int p4);
}
