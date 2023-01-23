//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIWebPageDescriptor extends nsISupports
{
    public static final String NS_IWEBPAGEDESCRIPTOR_IID = "{6f30b676-3710-4c2c-80b1-0395fb26516e}";
    public static final long DISPLAY_AS_SOURCE = 1L;
    public static final long DISPLAY_NORMAL = 2L;
    
    void loadPage(final nsISupports p0, final long p1);
    
    nsISupports getCurrentDescriptor();
}
