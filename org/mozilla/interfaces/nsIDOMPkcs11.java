//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDOMPkcs11 extends nsISupports
{
    public static final String NS_IDOMPKCS11_IID = "{9fd42950-25e7-11d4-8a7d-006008c844c3}";
    
    int deletemodule(final String p0);
    
    int addmodule(final String p0, final String p1, final int p2, final int p3);
}
