//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIPasswordManager extends nsISupports
{
    public static final String NS_IPASSWORDMANAGER_IID = "{173562f0-2173-11d5-a54c-0010a401eb10}";
    
    void addUser(final String p0, final String p1, final String p2);
    
    void removeUser(final String p0, final String p1);
    
    void addReject(final String p0);
    
    void removeReject(final String p0);
    
    nsISimpleEnumerator getEnumerator();
    
    nsISimpleEnumerator getRejectEnumerator();
}
