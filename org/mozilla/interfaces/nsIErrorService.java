//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIErrorService extends nsISupports
{
    public static final String NS_IERRORSERVICE_IID = "{e72f94b2-5f85-11d4-9877-00c04fa0cf4a}";
    
    void registerErrorStringBundle(final short p0, final String p1);
    
    void unregisterErrorStringBundle(final short p0);
    
    String getErrorStringBundle(final short p0);
    
    void registerErrorStringBundleKey(final long p0, final String p1);
    
    void unregisterErrorStringBundleKey(final long p0);
    
    String getErrorStringBundleKey(final long p0);
}
