//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsITokenPasswordDialogs extends nsISupports
{
    public static final String NS_ITOKENPASSWORDDIALOGS_IID = "{be26b580-1dd1-11b2-9946-c598d0d07727}";
    
    void setPassword(final nsIInterfaceRequestor p0, final String p1, final boolean[] p2);
    
    void getPassword(final nsIInterfaceRequestor p0, final String p1, final String[] p2, final boolean[] p3);
}
