//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIRandomAccessIterator extends nsISupports
{
    public static final String NS_IRANDOMACCESSITERATOR_IID = "{9bd6fdb0-1dd1-11b2-9101-d15375968230}";
    
    nsISupports getElement();
    
    nsISupports getElementAt(final int p0);
    
    void putElement(final nsISupports p0);
    
    void putElementAt(final int p0, final nsISupports p1);
    
    void stepForward();
    
    void stepForwardBy(final int p0);
    
    void stepBackward();
    
    void stepBackwardBy(final int p0);
    
    boolean isEqualTo(final nsISupports p0);
    
    nsISupports _clone();
}
