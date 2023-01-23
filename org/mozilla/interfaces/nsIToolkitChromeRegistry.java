//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIToolkitChromeRegistry extends nsIXULChromeRegistry
{
    public static final String NS_ITOOLKITCHROMEREGISTRY_IID = "{94490b3f-f094-418e-b1b9-73878d29bff3}";
    
    void processContentsManifest(final nsIURI p0, final nsIURI p1, final nsIURI p2, final boolean p3, final boolean p4);
    
    void checkForOSAccessibility();
    
    nsIUTF8StringEnumerator getLocalesForPackage(final String p0);
}
