//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIStringBundleOverride extends nsISupports
{
    public static final String NS_ISTRINGBUNDLEOVERRIDE_IID = "{965eb278-5678-456b-82a7-20a0c86a803c}";
    
    String getStringFromName(final String p0, final String p1);
    
    nsISimpleEnumerator enumerateKeysInBundle(final String p0);
}
