//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsITooltipListener extends nsISupports
{
    public static final String NS_ITOOLTIPLISTENER_IID = "{44b78386-1dd2-11b2-9ad2-e4eee2ca1916}";
    
    void onShowTooltip(final int p0, final int p1, final String p2);
    
    void onHideTooltip();
}
