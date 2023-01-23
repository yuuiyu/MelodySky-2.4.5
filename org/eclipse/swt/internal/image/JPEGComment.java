//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.image;

final class JPEGComment extends JPEGVariableSizeSegment
{
    public JPEGComment(final byte[] reference) {
        super(reference);
    }
    
    public JPEGComment(final LEDataInputStream byteStream) {
        super(byteStream);
    }
    
    @Override
    public int signature() {
        return 65534;
    }
}
