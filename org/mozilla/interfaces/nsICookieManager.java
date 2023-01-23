//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsICookieManager extends nsISupports
{
    public static final String NS_ICOOKIEMANAGER_IID = "{aaab6710-0f2c-11d5-a53b-0010a401eb10}";
    
    void removeAll();
    
    nsISimpleEnumerator getEnumerator();
    
    void remove(final String p0, final String p1, final String p2, final boolean p3);
}
