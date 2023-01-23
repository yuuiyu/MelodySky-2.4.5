//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIBidirectionalIterator extends nsISupports
{
    public static final String NS_IBIDIRECTIONALITERATOR_IID = "{948defaa-1dd1-11b2-89f6-8ce81f5ebda9}";
    
    nsISupports getElement();
    
    void putElement(final nsISupports p0);
    
    void stepForward();
    
    void stepBackward();
    
    boolean isEqualTo(final nsISupports p0);
    
    nsISupports _clone();
}
