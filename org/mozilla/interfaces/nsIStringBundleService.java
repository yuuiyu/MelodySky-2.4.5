//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIStringBundleService extends nsISupports
{
    public static final String NS_ISTRINGBUNDLESERVICE_IID = "{d85a17c0-aa7c-11d2-9b8c-00805f8a16d9}";
    
    nsIStringBundle createBundle(final String p0);
    
    nsIStringBundle createExtensibleBundle(final String p0);
    
    String formatStatusMessage(final long p0, final String p1);
    
    void flushBundles();
}
