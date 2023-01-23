//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIKeygenThread extends nsISupports
{
    public static final String NS_IKEYGENTHREAD_IID = "{8712a243-5539-447c-9f47-8653f40c3a09}";
    
    void startKeyGeneration(final nsIObserver p0);
    
    void userCanceled(final boolean[] p0);
}
