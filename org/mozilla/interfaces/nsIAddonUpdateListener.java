//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIAddonUpdateListener extends nsISupports
{
    public static final String NS_IADDONUPDATELISTENER_IID = "{bb86037c-98c1-4c22-8e03-1e4c9fc89a8e}";
    
    void onStateChange(final nsIUpdateItem p0, final short p1, final int p2);
    
    void onProgress(final nsIUpdateItem p0, final long p1, final long p2);
}
