//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIImageDocument extends nsISupports
{
    public static final String NS_IIMAGEDOCUMENT_IID = "{7b80eebc-c98e-4461-8bdb-6e3b6e828890}";
    
    boolean getImageResizingEnabled();
    
    boolean getImageIsOverflowing();
    
    boolean getImageIsResized();
    
    imgIRequest getImageRequest();
    
    void shrinkToFit();
    
    void restoreImage();
    
    void restoreImageTo(final int p0, final int p1);
    
    void toggleImageSize();
}
