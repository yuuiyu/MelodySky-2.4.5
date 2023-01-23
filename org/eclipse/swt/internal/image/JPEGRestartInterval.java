//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.image;

final class JPEGRestartInterval extends JPEGFixedSizeSegment
{
    public JPEGRestartInterval(final LEDataInputStream byteStream) {
        super(byteStream);
    }
    
    public int signature() {
        return 65501;
    }
    
    public int getRestartInterval() {
        return (this.reference[4] & 0xFF) << 8 | (this.reference[5] & 0xFF);
    }
    
    public int fixedSize() {
        return 6;
    }
}
