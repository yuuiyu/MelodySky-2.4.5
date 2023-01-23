//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIFontPackageService extends nsISupports
{
    public static final String NS_IFONTPACKAGESERVICE_IID = "{6712fdd2-f978-11d4-a144-005004832142}";
    
    void setHandler(final nsIFontPackageHandler p0);
    
    void fontPackageHandled(final boolean p0, final boolean p1, final String p2);
}
