//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface imgIContainer extends nsISupports
{
    public static final String IMGICONTAINER_IID = "{1a6290e6-8285-4e10-963d-d001f8d327b8}";
    public static final short kNormalAnimMode = 0;
    public static final short kDontAnimMode = 1;
    public static final short kLoopOnceAnimMode = 2;
    
    void init(final int p0, final int p1, final imgIContainerObserver p2);
    
    int getPreferredAlphaChannelFormat();
    
    int getWidth();
    
    int getHeight();
    
    gfxIImageFrame getCurrentFrame();
    
    long getNumFrames();
    
    int getAnimationMode();
    
    void setAnimationMode(final int p0);
    
    gfxIImageFrame getFrameAt(final long p0);
    
    void appendFrame(final gfxIImageFrame p0);
    
    void removeFrame(final gfxIImageFrame p0);
    
    void endFrameDecode(final long p0, final long p1);
    
    void decodingComplete();
    
    void clear();
    
    void startAnimation();
    
    void stopAnimation();
    
    void resetAnimation();
    
    int getLoopCount();
    
    void setLoopCount(final int p0);
}
