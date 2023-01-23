//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIXULOverlayProvider extends nsISupports
{
    public static final String NS_IXULOVERLAYPROVIDER_IID = "{1d5b5b94-dc47-4050-93b7-ac092e383cad}";
    
    nsISimpleEnumerator getXULOverlays(final nsIURI p0);
    
    nsISimpleEnumerator getStyleOverlays(final nsIURI p0);
}
