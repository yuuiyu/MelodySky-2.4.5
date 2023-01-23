//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIPopupWindowManager extends nsISupports
{
    public static final String NS_IPOPUPWINDOWMANAGER_IID = "{3210a6aa-b464-4f57-9335-b22815567cf1}";
    public static final long ALLOW_POPUP = 1L;
    public static final long DENY_POPUP = 2L;
    public static final long ALLOW_POPUP_WITH_PREJUDICE = 3L;
    
    long testPermission(final nsIURI p0);
}
