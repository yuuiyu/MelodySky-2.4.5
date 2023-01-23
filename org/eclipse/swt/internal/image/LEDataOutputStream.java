//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.image;

import java.io.*;

final class LEDataOutputStream extends OutputStream
{
    OutputStream out;
    
    public LEDataOutputStream(final OutputStream output) {
        this.out = output;
    }
    
    @Override
    public void write(final byte[] b, final int off, final int len) throws IOException {
        this.out.write(b, off, len);
    }
    
    @Override
    public void write(final int b) throws IOException {
        this.out.write(b);
    }
    
    public void writeByte(final byte b) throws IOException {
        this.out.write(b & 0xFF);
    }
    
    public void writeInt(final int theInt) throws IOException {
        this.out.write(theInt & 0xFF);
        this.out.write(theInt >> 8 & 0xFF);
        this.out.write(theInt >> 16 & 0xFF);
        this.out.write(theInt >> 24 & 0xFF);
    }
    
    public void writeShort(final int theShort) throws IOException {
        this.out.write(theShort & 0xFF);
        this.out.write(theShort >> 8 & 0xFF);
    }
}
