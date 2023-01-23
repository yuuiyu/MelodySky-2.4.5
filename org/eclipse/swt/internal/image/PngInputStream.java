//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.image;

import java.io.*;

public class PngInputStream extends InputStream
{
    PngChunkReader reader;
    PngChunk chunk;
    int offset;
    int length;
    static final int DATA_OFFSET = 8;
    
    public PngInputStream(final PngIdatChunk chunk, final PngChunkReader reader) {
        this.chunk = (PngChunk)chunk;
        this.reader = reader;
        this.length = chunk.getLength();
        this.offset = 0;
    }
    
    private boolean checkChunk() throws IOException {
        while (this.offset == this.length) {
            this.chunk = this.reader.readNextChunk();
            if (this.chunk == null) {
                throw new IOException();
            }
            if (this.chunk.getChunkType() == 3) {
                return false;
            }
            if (this.chunk.getChunkType() != 2) {
                throw new IOException();
            }
            this.length = this.chunk.getLength();
            this.offset = 0;
        }
        return true;
    }
    
    @Override
    public void close() throws IOException {
        this.chunk = null;
    }
    
    @Override
    public int read() throws IOException {
        if (this.chunk == null) {
            throw new IOException();
        }
        if (this.offset == this.length && !this.checkChunk()) {
            return -1;
        }
        final int b = this.chunk.reference[8 + this.offset] & 0xFF;
        ++this.offset;
        return b;
    }
    
    @Override
    public int read(final byte[] b, final int off, int len) throws IOException {
        if (this.chunk == null) {
            throw new IOException();
        }
        if (this.offset == this.length && !this.checkChunk()) {
            return -1;
        }
        len = Math.min(len, this.length - this.offset);
        System.arraycopy(this.chunk.reference, 8 + this.offset, b, off, len);
        this.offset += len;
        return len;
    }
}
