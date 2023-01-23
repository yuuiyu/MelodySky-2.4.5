//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIPasswordManagerInternal extends nsISupports
{
    public static final String NS_IPASSWORDMANAGERINTERNAL_IID = "{dc2ff152-85cb-474e-b4c2-86a3d48cf4d0}";
    
    void findPasswordEntry(final String p0, final String p1, final String p2, final String[] p3, final String[] p4, final String[] p5);
    
    void addUserFull(final String p0, final String p1, final String p2, final String p3, final String p4);
    
    void readPasswords(final nsIFile p0);
}
