//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.image;

final class JPEGStartOfImage extends JPEGFixedSizeSegment
{
    public JPEGStartOfImage() {
    }
    
    public JPEGStartOfImage(final byte[] reference) {
        super(reference);
    }
    
    public JPEGStartOfImage(final LEDataInputStream byteStream) {
        super(byteStream);
    }
    
    public int signature() {
        return 65496;
    }
    
    public int fixedSize() {
        return 2;
    }
}
