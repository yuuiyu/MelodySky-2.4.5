//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIForwardIterator extends nsISupports
{
    public static final String NS_IFORWARDITERATOR_IID = "{8da01646-1dd2-11b2-98a7-c7009045be7e}";
    
    nsISupports getElement();
    
    void putElement(final nsISupports p0);
    
    void stepForward();
    
    boolean isEqualTo(final nsISupports p0);
    
    nsISupports _clone();
}
