//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\�½��ļ��� (2)"!

//Decompiled by Procyon!

package pw.knx.feather.tessellate;

import java.nio.*;

public class ExpandingTess extends BasicTess
{
    private final float ratio;
    private final float factor;
    
    ExpandingTess(final int initial, final float ratio, final float factor) {
        super(initial);
        this.ratio = ratio;
        this.factor = factor;
    }
    
    public Tessellation addVertex(final float x, final float y, final float z) {
        int capacity = this.raw.length;
        if (this.index * 6 >= capacity * this.ratio) {
            capacity *= (int)this.factor;
            final int[] newBuffer = new int[capacity];
            System.arraycopy(this.raw, 0, newBuffer, 0, this.raw.length);
            this.raw = newBuffer;
            this.buffer = ByteBuffer.allocateDirect(capacity * 4).order(ByteOrder.nativeOrder());
            this.iBuffer = this.buffer.asIntBuffer();
            this.fBuffer = this.buffer.asFloatBuffer();
        }
        return super.addVertex(x, y, z);
    }
}
