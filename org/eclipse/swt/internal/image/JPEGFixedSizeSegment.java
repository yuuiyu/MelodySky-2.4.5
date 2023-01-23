//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.image;

import org.eclipse.swt.*;

abstract class JPEGFixedSizeSegment extends JPEGSegment
{
    public JPEGFixedSizeSegment() {
        this.reference = new byte[this.fixedSize()];
        this.setSegmentMarker(this.signature());
    }
    
    public JPEGFixedSizeSegment(final byte[] reference) {
        super(reference);
    }
    
    public JPEGFixedSizeSegment(final LEDataInputStream byteStream) {
        this.reference = new byte[this.fixedSize()];
        try {
            byteStream.read(this.reference);
        }
        catch (Exception e) {
            SWT.error(39, e);
        }
    }
    
    public abstract int fixedSize();
    
    @Override
    public int getSegmentLength() {
        return this.fixedSize() - 2;
    }
    
    @Override
    public void setSegmentLength(final int length) {
    }
}
