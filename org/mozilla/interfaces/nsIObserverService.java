//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIObserverService extends nsISupports
{
    public static final String NS_IOBSERVERSERVICE_IID = "{d07f5192-e3d1-11d2-8acd-00105a1b8860}";
    
    void addObserver(final nsIObserver p0, final String p1, final boolean p2);
    
    void removeObserver(final nsIObserver p0, final String p1);
    
    void notifyObservers(final nsISupports p0, final String p1, final String p2);
    
    nsISimpleEnumerator enumerateObservers(final String p0);
}
