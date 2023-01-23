//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.newdawn.slick;

import org.newdawn.slick.opengl.*;
import java.nio.*;

class lIllI implements ImageData
{
    final /* synthetic */ LoadableImageData val$data;
    final /* synthetic */ int val$dataHeight;
    final /* synthetic */ ByteBuffer val$imageBuffer;
    final /* synthetic */ int val$dataWidth;
    final /* synthetic */ BigImage this$0;
    
    lIllI(final BigImage this$0, final LoadableImageData val$data, final int val$dataHeight, final ByteBuffer val$imageBuffer, final int val$dataWidth) {
        this.this$0 = this$0;
        this.val$data = val$data;
        this.val$dataHeight = val$dataHeight;
        this.val$imageBuffer = val$imageBuffer;
        this.val$dataWidth = val$dataWidth;
    }
    
    @Override
    public int getDepth() {
        return this.val$data.getDepth();
    }
    
    @Override
    public int getHeight() {
        return this.val$dataHeight;
    }
    
    @Override
    public ByteBuffer getImageBufferData() {
        return this.val$imageBuffer;
    }
    
    @Override
    public int getTexHeight() {
        return this.val$dataHeight;
    }
    
    @Override
    public int getTexWidth() {
        return this.val$dataWidth;
    }
    
    @Override
    public int getWidth() {
        return this.val$dataWidth;
    }
}
