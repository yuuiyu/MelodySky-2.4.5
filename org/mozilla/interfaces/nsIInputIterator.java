//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIInputIterator extends nsISupports
{
    public static final String NS_IINPUTITERATOR_IID = "{85585e12-1dd2-11b2-a930-f6929058269a}";
    
    nsISupports getElement();
    
    void stepForward();
    
    boolean isEqualTo(final nsISupports p0);
    
    nsISupports _clone();
}
