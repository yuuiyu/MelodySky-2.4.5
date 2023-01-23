//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.image;

final class JPEGEndOfImage extends JPEGFixedSizeSegment
{
    public JPEGEndOfImage() {
    }
    
    public JPEGEndOfImage(final byte[] reference) {
        super(reference);
    }
    
    @Override
    public int signature() {
        return 65497;
    }
    
    @Override
    public int fixedSize() {
        return 2;
    }
}
