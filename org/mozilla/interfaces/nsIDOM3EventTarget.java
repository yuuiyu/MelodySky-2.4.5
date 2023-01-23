//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDOM3EventTarget extends nsISupports
{
    public static final String NS_IDOM3EVENTTARGET_IID = "{3e9c01a7-de97-4c3b-8294-b4bd9d7056d1}";
    
    void addGroupedEventListener(final String p0, final nsIDOMEventListener p1, final boolean p2, final nsIDOMEventGroup p3);
    
    void removeGroupedEventListener(final String p0, final nsIDOMEventListener p1, final boolean p2, final nsIDOMEventGroup p3);
    
    boolean canTrigger(final String p0);
    
    boolean isRegisteredHere(final String p0);
}
