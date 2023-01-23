//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.image;

final class JPEGAppn extends JPEGVariableSizeSegment
{
    public JPEGAppn(final byte[] reference) {
        super(reference);
    }
    
    public JPEGAppn(final LEDataInputStream byteStream) {
        super(byteStream);
    }
    
    @Override
    public boolean verify() {
        final int marker = this.getSegmentMarker();
        return marker >= 65504 && marker <= 65519;
    }
}
