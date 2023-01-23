//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDirIndexListener extends nsISupports
{
    public static final String NS_IDIRINDEXLISTENER_IID = "{fae4e9a8-1dd1-11b2-b53c-8f3aa1bbf8f5}";
    
    void onIndexAvailable(final nsIRequest p0, final nsISupports p1, final nsIDirIndex p2);
    
    void onInformationAvailable(final nsIRequest p0, final nsISupports p1, final String p2);
}
