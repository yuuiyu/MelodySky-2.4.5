//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIEnvironment extends nsISupports
{
    public static final String NS_IENVIRONMENT_IID = "{101d5941-d820-4e85-a266-9a3469940807}";
    
    void set(final String p0, final String p1);
    
    String get(final String p0);
    
    boolean exists(final String p0);
}
