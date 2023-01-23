//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsICookieConsent extends nsISupports
{
    public static final String NS_ICOOKIECONSENT_IID = "{f5a34f50-1f39-11d6-a627-0010a401eb10}";
    
    int getConsent(final nsIURI p0, final nsIHttpChannel p1, final boolean p2, final int[] p3);
}
