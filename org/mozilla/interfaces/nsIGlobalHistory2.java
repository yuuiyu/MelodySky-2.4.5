//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIGlobalHistory2 extends nsISupports
{
    public static final String NS_IGLOBALHISTORY2_IID = "{cf777d42-1270-4b34-be7b-2931c93feda5}";
    
    void addURI(final nsIURI p0, final boolean p1, final boolean p2, final nsIURI p3);
    
    boolean isVisited(final nsIURI p0);
    
    void setPageTitle(final nsIURI p0, final String p1);
}
