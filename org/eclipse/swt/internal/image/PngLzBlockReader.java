//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.image;

import java.io.*;

public class PngLzBlockReader
{
    boolean isLastBlock;
    byte compressionType;
    int uncompressedBytesRemaining;
    PngDecodingDataStream stream;
    PngHuffmanTables huffmanTables;
    byte[] window;
    int windowIndex;
    int copyIndex;
    int copyBytesRemaining;
    static final int UNCOMPRESSED = 0;
    static final int COMPRESSED_FIXED = 1;
    static final int COMPRESSED_DYNAMIC = 2;
    static final int END_OF_COMPRESSED_BLOCK = 256;
    static final int FIRST_LENGTH_CODE = 257;
    static final int LAST_LENGTH_CODE = 285;
    static final int FIRST_DISTANCE_CODE = 1;
    static final int LAST_DISTANCE_CODE = 29;
    static final int FIRST_CODE_LENGTH_CODE = 4;
    static final int LAST_CODE_LENGTH_CODE = 19;
    static final int[] lengthBases;
    static final int[] extraLengthBits;
    static final int[] distanceBases;
    static final int[] extraDistanceBits;
    
    PngLzBlockReader(final PngDecodingDataStream stream) {
        this.stream = stream;
        this.isLastBlock = false;
    }
    
    void setWindowSize(final int windowSize) {
        this.window = new byte[windowSize];
    }
    
    void readNextBlockHeader() throws IOException {
        this.isLastBlock = (this.stream.getNextIdatBit() != 0);
        this.compressionType = (byte)this.stream.getNextIdatBits(2);
        if (this.compressionType > 2) {
            this.stream.error();
        }
        if (this.compressionType == 0) {
            final byte b1 = this.stream.getNextIdatByte();
            final byte b2 = this.stream.getNextIdatByte();
            final byte b3 = this.stream.getNextIdatByte();
            final byte b4 = this.stream.getNextIdatByte();
            if (b1 != ~b3 || b2 != ~b4) {
                this.stream.error();
            }
            this.uncompressedBytesRemaining = ((b1 & 0xFF) | (b2 & 0xFF) << 8);
        }
        else if (this.compressionType == 2) {
            this.huffmanTables = PngHuffmanTables.getDynamicTables(this.stream);
        }
        else {
            this.huffmanTables = PngHuffmanTables.getFixedTables();
        }
    }
    
    byte getNextByte() throws IOException {
        if (this.compressionType != 0) {
            return this.getNextCompressedByte();
        }
        if (this.uncompressedBytesRemaining == 0) {
            this.readNextBlockHeader();
            return this.getNextByte();
        }
        --this.uncompressedBytesRemaining;
        return this.stream.getNextIdatByte();
    }
    
    private void assertBlockAtEnd() throws IOException {
        if (this.compressionType == 0) {
            if (this.uncompressedBytesRemaining > 0) {
                this.stream.error();
            }
        }
        else if (this.copyBytesRemaining > 0 || this.huffmanTables.getNextLiteralValue(this.stream) != 256) {
            this.stream.error();
        }
    }
    
    void assertCompressedDataAtEnd() throws IOException {
        this.assertBlockAtEnd();
        while (!this.isLastBlock) {
            this.readNextBlockHeader();
            this.assertBlockAtEnd();
        }
    }
    
    private byte getNextCompressedByte() throws IOException {
        if (this.copyBytesRemaining > 0) {
            final byte value = this.window[this.copyIndex];
            this.window[this.windowIndex] = value;
            --this.copyBytesRemaining;
            ++this.copyIndex;
            ++this.windowIndex;
            if (this.copyIndex == this.window.length) {
                this.copyIndex = 0;
            }
            if (this.windowIndex == this.window.length) {
                this.windowIndex = 0;
            }
            return value;
        }
        int value2 = this.huffmanTables.getNextLiteralValue(this.stream);
        if (value2 < 256) {
            this.window[this.windowIndex] = (byte)value2;
            ++this.windowIndex;
            if (this.windowIndex >= this.window.length) {
                this.windowIndex = 0;
            }
            return (byte)value2;
        }
        if (value2 == 256) {
            this.readNextBlockHeader();
            return this.getNextByte();
        }
        if (value2 <= 285) {
            int extraBits = PngLzBlockReader.extraLengthBits[value2 - 257];
            int length = PngLzBlockReader.lengthBases[value2 - 257];
            if (extraBits > 0) {
                length += this.stream.getNextIdatBits(extraBits);
            }
            value2 = this.huffmanTables.getNextDistanceValue(this.stream);
            if (value2 > 29) {
                this.stream.error();
            }
            extraBits = PngLzBlockReader.extraDistanceBits[value2];
            int distance = PngLzBlockReader.distanceBases[value2];
            if (extraBits > 0) {
                distance += this.stream.getNextIdatBits(extraBits);
            }
            this.copyIndex = this.windowIndex - distance;
            if (this.copyIndex < 0) {
                this.copyIndex += this.window.length;
            }
            this.copyBytesRemaining = length;
            return this.getNextCompressedByte();
        }
        this.stream.error();
        return 0;
    }
    
    static {
        lengthBases = new int[] { 3, 4, 5, 6, 7, 8, 9, 10, 11, 13, 15, 17, 19, 23, 27, 31, 35, 43, 51, 59, 67, 83, 99, 115, 131, 163, 195, 227, 258 };
        extraLengthBits = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5, 0 };
        distanceBases = new int[] { 1, 2, 3, 4, 5, 7, 9, 13, 17, 25, 33, 49, 65, 97, 129, 193, 257, 385, 513, 769, 1025, 1537, 2049, 3073, 4097, 6145, 8193, 12289, 16385, 24577 };
        extraDistanceBits = new int[] { 0, 0, 0, 0, 1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7, 8, 8, 9, 9, 10, 10, 11, 11, 12, 12, 13, 13 };
    }
}
