//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIScreen extends nsISupports
{
    public static final String NS_ISCREEN_IID = "{f728830e-1dd1-11b2-9598-fb9f414f2465}";
    
    void getRect(final int[] p0, final int[] p1, final int[] p2, final int[] p3);
    
    void getAvailRect(final int[] p0, final int[] p1, final int[] p2, final int[] p3);
    
    int getPixelDepth();
    
    int getColorDepth();
}
