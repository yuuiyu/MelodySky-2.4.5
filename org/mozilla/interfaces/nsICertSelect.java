//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsICertSelect extends nsISupports
{
    public static final String NS_ICERTSELECT_IID = "{3cac403c-edb3-11d4-998b-00b0d02354a0}";
    
    nsIX509Cert selectClientAuthCert(final nsISupports p0, final long p1, final nsIX509Cert[] p2);
}
