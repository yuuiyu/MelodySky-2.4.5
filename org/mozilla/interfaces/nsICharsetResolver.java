//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsICharsetResolver extends nsISupports
{
    public static final String NS_ICHARSETRESOLVER_IID = "{d143a084-b626-4614-845f-41f3ca43a674}";
    
    String requestCharset(final nsIWebNavigation p0, final nsIChannel p1, final boolean[] p2, final nsISupports[] p3);
    
    void notifyResolvedCharset(final String p0, final nsISupports p1);
}
