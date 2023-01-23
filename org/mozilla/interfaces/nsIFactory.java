//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIFactory extends nsISupports
{
    public static final String NS_IFACTORY_IID = "{00000001-0000-0000-c000-000000000046}";
    
    nsISupports createInstance(final nsISupports p0, final String p1);
    
    void lockFactory(final boolean p0);
}
