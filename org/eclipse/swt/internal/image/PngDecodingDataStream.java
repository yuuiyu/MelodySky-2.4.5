//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.image;

import java.io.*;
import org.eclipse.swt.*;

public class PngDecodingDataStream extends InputStream
{
    InputStream stream;
    byte currentByte;
    int nextBitIndex;
    PngLzBlockReader lzBlockReader;
    int adlerValue;
    static final int PRIME = 65521;
    static final int MAX_BIT = 7;
    
    PngDecodingDataStream(final InputStream stream) throws IOException {
        this.stream = stream;
        this.nextBitIndex = 8;
        this.adlerValue = 1;
        this.lzBlockReader = new PngLzBlockReader(this);
        this.readCompressedDataHeader();
        this.lzBlockReader.readNextBlockHeader();
    }
    
    void assertImageDataAtEnd() throws IOException {
        this.lzBlockReader.assertCompressedDataAtEnd();
    }
    
    @Override
    public void close() throws IOException {
        this.assertImageDataAtEnd();
        this.checkAdler();
    }
    
    int getNextIdatBits(final int length) throws IOException {
        int value = 0;
        for (int i = 0; i < length; ++i) {
            value |= this.getNextIdatBit() << i;
        }
        return value;
    }
    
    int getNextIdatBit() throws IOException {
        if (this.nextBitIndex > 7) {
            this.currentByte = this.getNextIdatByte();
            this.nextBitIndex = 0;
        }
        return (this.currentByte & 1 << this.nextBitIndex) >> this.nextBitIndex++;
    }
    
    byte getNextIdatByte() throws IOException {
        final byte nextByte = (byte)this.stream.read();
        this.nextBitIndex = 8;
        return nextByte;
    }
    
    void updateAdler(final byte value) {
        int low = this.adlerValue & 0xFFFF;
        int high = this.adlerValue >> 16 & 0xFFFF;
        final int valueInt = value & 0xFF;
        low = (low + valueInt) % 65521;
        high = (low + high) % 65521;
        this.adlerValue = (high << 16 | low);
    }
    
    @Override
    public int read() throws IOException {
        final byte nextDecodedByte = this.lzBlockReader.getNextByte();
        this.updateAdler(nextDecodedByte);
        return nextDecodedByte & 0xFF;
    }
    
    @Override
    public int read(final byte[] buffer, final int off, final int len) throws IOException {
        for (int i = 0; i < len; ++i) {
            final int b = this.read();
            if (b == -1) {
                return i;
            }
            buffer[off + i] = (byte)b;
        }
        return len;
    }
    
    void error() {
        SWT.error(40);
    }
    
    private void readCompressedDataHeader() throws IOException {
        final byte headerByte1 = this.getNextIdatByte();
        final byte headerByte2 = this.getNextIdatByte();
        final int number = (headerByte1 & 0xFF) << 8 | (headerByte2 & 0xFF);
        if (number % 31 != 0) {
            this.error();
        }
        final int compressionMethod = headerByte1 & 0xF;
        if (compressionMethod != 8) {
            this.error();
        }
        final int windowSizeHint = (headerByte1 & 0xF0) >> 4;
        if (windowSizeHint > 7) {
            this.error();
        }
        final int windowSize = 1 << windowSizeHint + 8;
        this.lzBlockReader.setWindowSize(windowSize);
        final int dictionary = headerByte2 & 0x20;
        if (dictionary != 0) {
            this.error();
        }
    }
    
    void checkAdler() throws IOException {
        final int storedAdler = (this.getNextIdatByte() & 0xFF) << 24 | (this.getNextIdatByte() & 0xFF) << 16 | (this.getNextIdatByte() & 0xFF) << 8 | (this.getNextIdatByte() & 0xFF);
        if (storedAdler != this.adlerValue) {
            this.error();
        }
    }
}
