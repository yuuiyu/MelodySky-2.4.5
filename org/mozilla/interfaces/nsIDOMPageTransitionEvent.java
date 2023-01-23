//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDOMPageTransitionEvent extends nsIDOMEvent
{
    public static final String NS_IDOMPAGETRANSITIONEVENT_IID = "{b712418b-376f-4f75-b156-5d9ad99fe51f}";
    
    boolean getPersisted();
    
    void initPageTransitionEvent(final String p0, final boolean p1, final boolean p2, final boolean p3);
}
