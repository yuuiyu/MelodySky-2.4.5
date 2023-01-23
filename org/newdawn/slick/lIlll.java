//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.newdawn.slick;

import org.newdawn.slick.opengl.*;
import java.nio.*;

class lIlll implements ImageData
{
    final /* synthetic */ LoadableImageData val$data;
    final /* synthetic */ int val$imageHeight;
    final /* synthetic */ int val$imageWidth;
    final /* synthetic */ ByteBuffer val$subBuffer;
    final /* synthetic */ int val$ySize;
    final /* synthetic */ int val$xSize;
    final /* synthetic */ BigImage this$0;
    
    lIlll(final BigImage this$0, final LoadableImageData val$data, final int val$imageHeight, final int val$imageWidth, final ByteBuffer val$subBuffer, final int val$ySize, final int val$xSize) {
        this.this$0 = this$0;
        this.val$data = val$data;
        this.val$imageHeight = val$imageHeight;
        this.val$imageWidth = val$imageWidth;
        this.val$subBuffer = val$subBuffer;
        this.val$ySize = val$ySize;
        this.val$xSize = val$xSize;
    }
    
    @Override
    public int getDepth() {
        return this.val$data.getDepth();
    }
    
    @Override
    public int getHeight() {
        return this.val$imageHeight;
    }
    
    @Override
    public int getWidth() {
        return this.val$imageWidth;
    }
    
    @Override
    public ByteBuffer getImageBufferData() {
        return this.val$subBuffer;
    }
    
    @Override
    public int getTexHeight() {
        return this.val$ySize;
    }
    
    @Override
    public int getTexWidth() {
        return this.val$xSize;
    }
}
