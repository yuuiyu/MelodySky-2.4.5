//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIScreenManager extends nsISupports
{
    public static final String NS_ISCREENMANAGER_IID = "{662e7b78-1dd2-11b2-a3d3-fc1e5f5fb9d4}";
    
    nsIScreen screenForRect(final int p0, final int p1, final int p2, final int p3);
    
    nsIScreen getPrimaryScreen();
    
    long getNumberOfScreens();
}
