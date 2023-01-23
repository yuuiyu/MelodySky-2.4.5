//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIWindowMediatorListener extends nsISupports
{
    public static final String NS_IWINDOWMEDIATORLISTENER_IID = "{2f276982-0d60-4377-a595-d350ba516395}";
    
    void onWindowTitleChange(final nsIXULWindow p0, final String p1);
    
    void onOpenWindow(final nsIXULWindow p0);
    
    void onCloseWindow(final nsIXULWindow p0);
}
