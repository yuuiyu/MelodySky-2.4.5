//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.image;

import java.io.*;

final class LEDataInputStream extends InputStream
{
    int position;
    InputStream in;
    protected byte[] buf;
    protected int pos;
    
    public LEDataInputStream(final InputStream input) {
        this(input, 512);
    }
    
    public LEDataInputStream(final InputStream input, final int bufferSize) {
        this.in = input;
        if (bufferSize > 0) {
            this.buf = new byte[bufferSize];
            this.pos = bufferSize;
            return;
        }
        throw new IllegalArgumentException();
    }
    
    @Override
    public void close() throws IOException {
        this.buf = null;
        if (this.in != null) {
            this.in.close();
            this.in = null;
        }
    }
    
    public int getPosition() {
        return this.position;
    }
    
    @Override
    public int available() throws IOException {
        if (this.buf == null) {
            throw new IOException();
        }
        return this.buf.length - this.pos + this.in.available();
    }
    
    @Override
    public int read() throws IOException {
        if (this.buf == null) {
            throw new IOException();
        }
        if (this.pos < this.buf.length) {
            ++this.position;
            return this.buf[this.pos++] & 0xFF;
        }
        final int c = this.in.read();
        if (c != -1) {
            ++this.position;
        }
        return c;
    }
    
    @Override
    public int read(final byte[] b, int off, final int len) throws IOException {
        int read;
        int count;
        for (read = 0; read != len && (count = this.readData(b, off, len - read)) != -1; off += count, read += count) {}
        this.position += read;
        if (read == 0 && read != len) {
            return -1;
        }
        return read;
    }
    
    private int readData(final byte[] buffer, final int offset, final int length) throws IOException {
        if (this.buf == null) {
            throw new IOException();
        }
        if (offset < 0 || offset > buffer.length || length < 0 || length > buffer.length - offset) {
            throw new ArrayIndexOutOfBoundsException();
        }
        int cacheCopied = 0;
        int newOffset = offset;
        final int available = this.buf.length - this.pos;
        if (available > 0) {
            cacheCopied = ((available >= length) ? length : available);
            System.arraycopy(this.buf, this.pos, buffer, newOffset, cacheCopied);
            newOffset += cacheCopied;
            this.pos += cacheCopied;
        }
        if (cacheCopied == length) {
            return length;
        }
        final int inCopied = this.in.read(buffer, newOffset, length - cacheCopied);
        if (inCopied > 0) {
            return inCopied + cacheCopied;
        }
        if (cacheCopied == 0) {
            return inCopied;
        }
        return cacheCopied;
    }
    
    public int readInt() throws IOException {
        final byte[] buf = new byte[4];
        this.read(buf);
        return (buf[3] & 0xFF) << 24 | (buf[2] & 0xFF) << 16 | (buf[1] & 0xFF) << 8 | (buf[0] & 0xFF);
    }
    
    public short readShort() throws IOException {
        final byte[] buf = new byte[2];
        this.read(buf);
        return (short)((buf[1] & 0xFF) << 8 | (buf[0] & 0xFF));
    }
    
    public void unread(final byte[] b) throws IOException {
        final int length = b.length;
        if (length > this.pos) {
            throw new IOException();
        }
        this.position -= length;
        this.pos -= length;
        System.arraycopy(b, 0, this.buf, this.pos, length);
    }
}
