//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface gfxIImageFrame extends nsISupports
{
    public static final String GFXIIMAGEFRAME_IID = "{f6d00ee7-defc-4101-b2dc-e72cf4c37c3c}";
    
    void init(final int p0, final int p1, final int p2, final int p3, final int p4, final int p5);
    
    boolean getMutable();
    
    void setMutable(final boolean p0);
    
    int getX();
    
    int getY();
    
    int getWidth();
    
    int getHeight();
    
    int getFormat();
    
    boolean getNeedsBackground();
    
    long getImageBytesPerRow();
    
    long getImageDataLength();
    
    void getImageData(final short[][] p0, final long[] p1);
    
    void setImageData(final short[] p0, final long p1, final int p2);
    
    void lockImageData();
    
    void unlockImageData();
    
    long getAlphaBytesPerRow();
    
    long getAlphaDataLength();
    
    void getAlphaData(final short[][] p0, final long[] p1);
    
    void setAlphaData(final short[] p0, final long p1, final int p2);
    
    void lockAlphaData();
    
    void unlockAlphaData();
    
    void drawTo(final gfxIImageFrame p0, final int p1, final int p2, final int p3, final int p4);
    
    int getTimeout();
    
    void setTimeout(final int p0);
    
    int getFrameDisposalMethod();
    
    void setFrameDisposalMethod(final int p0);
    
    long getBackgroundColor();
    
    void setBackgroundColor(final long p0);
}
