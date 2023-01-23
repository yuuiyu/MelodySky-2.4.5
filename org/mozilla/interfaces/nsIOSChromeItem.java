//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIOSChromeItem extends nsISupports
{
    public static final String NS_IOSCHROMEITEM_IID = "{ddd6790a-1dd1-11b2-a804-b522643903b9}";
    
    String getName();
    
    boolean getHidden();
    
    void setHidden(final boolean p0);
    
    int getX();
    
    int getY();
    
    int getWidth();
    
    int getHeight();
}
