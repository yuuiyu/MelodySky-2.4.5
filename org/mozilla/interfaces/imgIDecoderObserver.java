//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface imgIDecoderObserver extends imgIContainerObserver
{
    public static final String IMGIDECODEROBSERVER_IID = "{cce7152e-4395-4231-a781-c347c5446cc2}";
    
    void onStartDecode(final imgIRequest p0);
    
    void onStartContainer(final imgIRequest p0, final imgIContainer p1);
    
    void onStartFrame(final imgIRequest p0, final gfxIImageFrame p1);
    
    void onStopFrame(final imgIRequest p0, final gfxIImageFrame p1);
    
    void onStopContainer(final imgIRequest p0, final imgIContainer p1);
    
    void onStopDecode(final imgIRequest p0, final long p1, final String p2);
}
