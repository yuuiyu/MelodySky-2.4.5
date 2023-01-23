//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.image;

import org.eclipse.swt.*;

final class TIFFModifiedHuffmanCodec
{
    static final short[][][] BLACK_CODE;
    static final short[][][] WHITE_CODE;
    static final int BLACK_MIN_BITS = 2;
    static final int WHITE_MIN_BITS = 4;
    boolean isWhite;
    int whiteValue;
    int blackValue;
    byte[] src;
    byte[] dest;
    int byteOffsetSrc;
    int bitOffsetSrc;
    int byteOffsetDest;
    int bitOffsetDest;
    int code;
    int nbrBits;
    int rowSize;
    
    TIFFModifiedHuffmanCodec() {
        this.whiteValue = 0;
        this.blackValue = 1;
        this.byteOffsetSrc = 0;
        this.bitOffsetSrc = 0;
        this.byteOffsetDest = 0;
        this.bitOffsetDest = 0;
        this.code = 0;
        this.nbrBits = 0;
    }
    
    public int decode(final byte[] src, final byte[] dest, final int offsetDest, final int rowSize, final int nRows) {
        this.src = src;
        this.dest = dest;
        this.rowSize = rowSize;
        this.byteOffsetSrc = 0;
        this.bitOffsetSrc = 0;
        this.byteOffsetDest = offsetDest;
        this.bitOffsetDest = 0;
        int cnt = 0;
        while (cnt < nRows && this.decodeRow()) {
            ++cnt;
            if (this.bitOffsetDest > 0) {
                ++this.byteOffsetDest;
                this.bitOffsetDest = 0;
            }
        }
        return this.byteOffsetDest - offsetDest;
    }
    
    boolean decodeRow() {
        this.isWhite = true;
        int n = 0;
        while (n < this.rowSize) {
            final int runLength = this.decodeRunLength();
            if (runLength < 0) {
                return false;
            }
            n += runLength;
            this.setNextBits(this.isWhite ? this.whiteValue : this.blackValue, runLength);
            this.isWhite = !this.isWhite;
        }
        return true;
    }
    
    int decodeRunLength() {
        int runLength = 0;
        int partialRun = 0;
        final short[][][] huffmanCode = this.isWhite ? TIFFModifiedHuffmanCodec.WHITE_CODE : TIFFModifiedHuffmanCodec.BLACK_CODE;
        while (true) {
            boolean found = false;
            this.nbrBits = (this.isWhite ? 4 : 2);
            this.code = this.getNextBits(this.nbrBits);
            for (final short[][] batch : huffmanCode) {
                final short[][] array2 = batch;
                final int length2 = array2.length;
                int j = 0;
                while (j < length2) {
                    final short[] element = array2[j];
                    if (element[0] == this.code) {
                        found = true;
                        partialRun = element[1];
                        if (partialRun == -1) {
                            if (this.byteOffsetSrc == this.src.length - 1) {
                                return -1;
                            }
                            break;
                        }
                        else {
                            runLength += partialRun;
                            if (partialRun < 64) {
                                return runLength;
                            }
                            break;
                        }
                    }
                    else {
                        ++j;
                    }
                }
                if (found) {
                    break;
                }
                this.code = (this.code << 1 | this.getNextBit());
            }
            if (!found) {
                SWT.error(40);
            }
        }
    }
    
    int getNextBit() {
        final int value = this.src[this.byteOffsetSrc] >>> 7 - this.bitOffsetSrc & 0x1;
        ++this.bitOffsetSrc;
        if (this.bitOffsetSrc > 7) {
            ++this.byteOffsetSrc;
            this.bitOffsetSrc = 0;
        }
        return value;
    }
    
    int getNextBits(final int cnt) {
        int value = 0;
        for (int i = 0; i < cnt; ++i) {
            value = (value << 1 | this.getNextBit());
        }
        return value;
    }
    
    void setNextBits(final int value, final int cnt) {
        int n;
        for (n = cnt; this.bitOffsetDest > 0 && this.bitOffsetDest <= 7 && n > 0; --n, ++this.bitOffsetDest) {
            this.dest[this.byteOffsetDest] = ((value == 1) ? ((byte)(this.dest[this.byteOffsetDest] | 1 << 7 - this.bitOffsetDest)) : ((byte)(this.dest[this.byteOffsetDest] & ~(1 << 7 - this.bitOffsetDest))));
        }
        if (this.bitOffsetDest == 8) {
            ++this.byteOffsetDest;
            this.bitOffsetDest = 0;
        }
        while (n >= 8) {
            this.dest[this.byteOffsetDest++] = (byte)((value == 1) ? 255 : 0);
            n -= 8;
        }
        while (n > 0) {
            this.dest[this.byteOffsetDest] = ((value == 1) ? ((byte)(this.dest[this.byteOffsetDest] | 1 << 7 - this.bitOffsetDest)) : ((byte)(this.dest[this.byteOffsetDest] & ~(1 << 7 - this.bitOffsetDest))));
            --n;
            ++this.bitOffsetDest;
        }
    }
    
    static {
        BLACK_CODE = new short[][][] { { { 2, 3 }, { 3, 2 } }, { { 2, 1 }, { 3, 4 } }, { { 2, 6 }, { 3, 5 } }, { { 3, 7 } }, { { 4, 9 }, { 5, 8 } }, { { 4, 10 }, { 5, 11 }, { 7, 12 } }, { { 4, 13 }, { 7, 14 } }, { { 24, 15 } }, { { 8, 18 }, { 15, 64 }, { 23, 16 }, { 24, 17 }, { 55, 0 } }, { { 0, -1 }, { 8, 1792 }, { 23, 24 }, { 24, 25 }, { 40, 23 }, { 55, 22 }, { 103, 19 }, { 104, 20 }, { 108, 21 }, { 12, 1856 }, { 13, 1920 } }, { { 18, 1984 }, { 19, 2048 }, { 20, 2112 }, { 21, 2176 }, { 22, 2240 }, { 23, 2304 }, { 28, 2368 }, { 29, 2432 }, { 30, 2496 }, { 31, 2560 }, { 36, 52 }, { 39, 55 }, { 40, 56 }, { 43, 59 }, { 44, 60 }, { 51, 320 }, { 52, 384 }, { 53, 448 }, { 55, 53 }, { 56, 54 }, { 82, 50 }, { 83, 51 }, { 84, 44 }, { 85, 45 }, { 86, 46 }, { 87, 47 }, { 88, 57 }, { 89, 58 }, { 90, 61 }, { 91, 256 }, { 100, 48 }, { 101, 49 }, { 102, 62 }, { 103, 63 }, { 104, 30 }, { 105, 31 }, { 106, 32 }, { 107, 33 }, { 108, 40 }, { 109, 41 }, { 200, 128 }, { 201, 192 }, { 202, 26 }, { 203, 27 }, { 204, 28 }, { 205, 29 }, { 210, 34 }, { 211, 35 }, { 212, 36 }, { 213, 37 }, { 214, 38 }, { 215, 39 }, { 218, 42 }, { 219, 43 } }, { { 74, 640 }, { 75, 704 }, { 76, 768 }, { 77, 832 }, { 82, 1280 }, { 83, 1344 }, { 84, 1408 }, { 85, 1472 }, { 90, 1536 }, { 91, 1600 }, { 100, 1664 }, { 101, 1728 }, { 108, 512 }, { 109, 576 }, { 114, 896 }, { 115, 960 }, { 116, 1024 }, { 117, 1088 }, { 118, 1152 }, { 119, 1216 } } };
        WHITE_CODE = new short[][][] { { { 7, 2 }, { 8, 3 }, { 11, 4 }, { 12, 5 }, { 14, 6 }, { 15, 7 } }, { { 7, 10 }, { 8, 11 }, { 18, 128 }, { 19, 8 }, { 20, 9 }, { 27, 64 } }, { { 3, 13 }, { 7, 1 }, { 8, 12 }, { 23, 192 }, { 24, 1664 }, { 42, 16 }, { 43, 17 }, { 52, 14 }, { 53, 15 } }, { { 3, 22 }, { 4, 23 }, { 8, 20 }, { 12, 19 }, { 19, 26 }, { 23, 21 }, { 24, 28 }, { 36, 27 }, { 39, 18 }, { 40, 24 }, { 43, 25 }, { 55, 256 } }, { { 2, 29 }, { 3, 30 }, { 4, 45 }, { 5, 46 }, { 10, 47 }, { 11, 48 }, { 18, 33 }, { 19, 34 }, { 20, 35 }, { 21, 36 }, { 22, 37 }, { 23, 38 }, { 26, 31 }, { 27, 32 }, { 36, 53 }, { 37, 54 }, { 40, 39 }, { 41, 40 }, { 42, 41 }, { 43, 42 }, { 44, 43 }, { 45, 44 }, { 50, 61 }, { 51, 62 }, { 52, 63 }, { 53, 0 }, { 54, 320 }, { 55, 384 }, { 74, 59 }, { 75, 60 }, { 82, 49 }, { 83, 50 }, { 84, 51 }, { 85, 52 }, { 88, 55 }, { 89, 56 }, { 90, 57 }, { 91, 58 }, { 100, 448 }, { 101, 512 }, { 103, 640 }, { 104, 576 } }, { { 152, 1472 }, { 153, 1536 }, { 154, 1600 }, { 155, 1728 }, { 204, 704 }, { 205, 768 }, { 210, 832 }, { 211, 896 }, { 212, 960 }, { 213, 1024 }, { 214, 1088 }, { 215, 1152 }, { 216, 1216 }, { 217, 1280 }, { 218, 1344 }, { 219, 1408 } }, new short[0][], { { 8, 1792 }, { 12, 1856 }, { 13, 1920 } }, { { 1, -1 }, { 18, 1984 }, { 19, 2048 }, { 20, 2112 }, { 21, 2176 }, { 22, 2240 }, { 23, 2304 }, { 28, 2368 }, { 29, 2432 }, { 30, 2496 }, { 31, 2560 } } };
    }
}
