//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.image;

import java.io.*;

public class PngDeflater
{
    static final int BASE = 65521;
    static final int WINDOW = 32768;
    static final int MIN_LENGTH = 3;
    static final int MAX_MATCHES = 32;
    static final int HASH = 8209;
    byte[] in;
    int inLength;
    ByteArrayOutputStream bytes;
    int adler32;
    int buffer;
    int bitCount;
    Link[] hashtable;
    Link[] window;
    int nextWindow;
    static final short[] mirrorBytes;
    static final Code[] lengthCodes;
    static final Code[] distanceCodes;
    
    public PngDeflater() {
        this.bytes = new ByteArrayOutputStream(1024);
        this.adler32 = 1;
        this.hashtable = new Link[8209];
        this.window = new Link[32768];
    }
    
    void writeShortLSB(final ByteArrayOutputStream baos, final int theShort) {
        final byte byte1 = (byte)(theShort & 0xFF);
        final byte byte2 = (byte)(theShort >> 8 & 0xFF);
        final byte[] temp = { byte1, byte2 };
        baos.write(temp, 0, 2);
    }
    
    void writeInt(final ByteArrayOutputStream baos, final int theInt) {
        final byte byte1 = (byte)(theInt >> 24 & 0xFF);
        final byte byte2 = (byte)(theInt >> 16 & 0xFF);
        final byte byte3 = (byte)(theInt >> 8 & 0xFF);
        final byte byte4 = (byte)(theInt & 0xFF);
        final byte[] temp = { byte1, byte2, byte3, byte4 };
        baos.write(temp, 0, 4);
    }
    
    void updateAdler(final byte value) {
        int low = this.adler32 & 0xFFFF;
        int high = this.adler32 >> 16 & 0xFFFF;
        final int valueInt = value & 0xFF;
        low = (low + valueInt) % 65521;
        high = (low + high) % 65521;
        this.adler32 = (high << 16 | low);
    }
    
    int hash(final byte[] bytes) {
        int hash = ((bytes[0] & 0xFF) << 24 | (bytes[1] & 0xFF) << 16 | (bytes[2] & 0xFF) << 8) % 8209;
        if (hash < 0) {
            hash += 8209;
        }
        return hash;
    }
    
    void writeBits(final int value, final int count) {
        this.buffer |= value << this.bitCount;
        this.bitCount += count;
        if (this.bitCount >= 16) {
            this.bytes.write((byte)this.buffer);
            this.bytes.write((byte)(this.buffer >>> 8));
            this.buffer >>>= 16;
            this.bitCount -= 16;
        }
    }
    
    void alignToByte() {
        if (this.bitCount > 0) {
            this.bytes.write((byte)this.buffer);
            if (this.bitCount > 8) {
                this.bytes.write((byte)(this.buffer >>> 8));
            }
        }
        this.buffer = 0;
        this.bitCount = 0;
    }
    
    void outputLiteral(final byte literal) {
        final int i = literal & 0xFF;
        if (i <= 143) {
            this.writeBits(PngDeflater.mirrorBytes[48 + i], 8);
        }
        else {
            this.writeBits(1 + 2 * PngDeflater.mirrorBytes[0 + i], 9);
        }
    }
    
    Code findCode(final int value, final Code[] codes) {
        int i = -1;
        int j = codes.length;
        int k;
        while (true) {
            k = (j + i) / 2;
            if (value < codes[k].min) {
                j = k;
            }
            else {
                if (value <= codes[k].max) {
                    break;
                }
                i = k;
            }
        }
        return codes[k];
    }
    
    void outputMatch(int length, final int distance) {
        while (length > 0) {
            int thisLength;
            if (length > 260) {
                thisLength = 258;
            }
            else if (length <= 258) {
                thisLength = length;
            }
            else {
                thisLength = length - 3;
            }
            length -= thisLength;
            final Code l = this.findCode(thisLength, PngDeflater.lengthCodes);
            if (l.code <= 279) {
                this.writeBits(PngDeflater.mirrorBytes[(l.code - 256) * 2], 7);
            }
            else {
                this.writeBits(PngDeflater.mirrorBytes[-88 + l.code], 8);
            }
            if (l.extraBits != 0) {
                this.writeBits(thisLength - l.min, l.extraBits);
            }
            final Code d = this.findCode(distance, PngDeflater.distanceCodes);
            this.writeBits(PngDeflater.mirrorBytes[d.code * 8], 5);
            if (d.extraBits != 0) {
                this.writeBits(distance - d.min, d.extraBits);
            }
        }
    }
    
    Match findLongestMatch(final int position, final Link firstPosition) {
        Link link = firstPosition;
        int numberOfMatches = 0;
        final Match bestMatch = new Match(-1, -1);
        do {
            final int matchPosition = link.value;
            if (position - matchPosition < 32768 && matchPosition != 0) {
                int i;
                for (i = 1; position + i < this.inLength && this.in[position + i] == this.in[matchPosition + i]; ++i) {}
                if (i >= 3) {
                    if (i > bestMatch.length) {
                        bestMatch.length = i;
                        bestMatch.distance = position - matchPosition;
                    }
                    if (++numberOfMatches == 32) {
                        break;
                    }
                }
            }
            link = link.next;
        } while (link != null);
        if (bestMatch.length < 3 || bestMatch.distance < 1 || bestMatch.distance > 32768) {
            return null;
        }
        return bestMatch;
    }
    
    void updateHashtable(final int to, final int from) {
        final byte[] data = new byte[3];
        for (int i = to; i < from && i + 3 <= this.inLength; ++i) {
            data[0] = this.in[i];
            data[1] = this.in[i + 1];
            data[2] = this.in[i + 2];
            final int hash = this.hash(data);
            if (this.window[this.nextWindow].previous != null) {
                this.window[this.nextWindow].previous.next = null;
            }
            else if (this.window[this.nextWindow].hash != 0) {
                this.hashtable[this.window[this.nextWindow].hash].next = null;
            }
            this.window[this.nextWindow].hash = hash;
            this.window[this.nextWindow].value = i;
            this.window[this.nextWindow].previous = null;
            final Link link = this.window[this.nextWindow];
            final Link next = this.hashtable[hash].next;
            link.next = next;
            final Link temp = next;
            this.hashtable[hash].next = this.window[this.nextWindow];
            if (temp != null) {
                temp.previous = this.window[this.nextWindow];
            }
            ++this.nextWindow;
            if (this.nextWindow == 32768) {
                this.nextWindow = 0;
            }
        }
    }
    
    void compress() {
        final byte[] data = new byte[3];
        for (int i = 0; i < 8209; ++i) {
            this.hashtable[i] = new Link();
        }
        for (int i = 0; i < 32768; ++i) {
            this.window[i] = new Link();
        }
        this.nextWindow = 0;
        int deferredPosition = -1;
        Match deferredMatch = null;
        this.writeBits(1, 1);
        this.writeBits(1, 2);
        this.outputLiteral(this.in[0]);
        int position = 1;
        while (position < this.inLength) {
            if (this.inLength - position < 3) {
                this.outputLiteral(this.in[position]);
                ++position;
            }
            else {
                data[0] = this.in[position];
                data[1] = this.in[position + 1];
                data[2] = this.in[position + 2];
                final int hash = this.hash(data);
                final Link firstPosition = this.hashtable[hash];
                final Match match = this.findLongestMatch(position, firstPosition);
                this.updateHashtable(position, position + 1);
                if (match != null) {
                    if (deferredMatch != null) {
                        if (match.length > deferredMatch.length + 1) {
                            this.outputLiteral(this.in[deferredPosition]);
                            deferredPosition = position;
                            deferredMatch = match;
                            ++position;
                        }
                        else {
                            this.outputMatch(deferredMatch.length, deferredMatch.distance);
                            final int newPosition = deferredPosition + deferredMatch.length;
                            deferredPosition = -1;
                            deferredMatch = null;
                            this.updateHashtable(position + 1, newPosition);
                            position = newPosition;
                        }
                    }
                    else {
                        deferredPosition = position;
                        deferredMatch = match;
                        ++position;
                    }
                }
                else if (deferredMatch != null) {
                    this.outputMatch(deferredMatch.length, deferredMatch.distance);
                    final int newPosition = deferredPosition + deferredMatch.length;
                    deferredPosition = -1;
                    deferredMatch = null;
                    this.updateHashtable(position + 1, newPosition);
                    position = newPosition;
                }
                else {
                    this.outputLiteral(this.in[position]);
                    ++position;
                }
            }
        }
        this.writeBits(0, 7);
        this.alignToByte();
    }
    
    void compressHuffmanOnly() {
        this.writeBits(1, 1);
        this.writeBits(1, 2);
        for (int position = 0; position < this.inLength; ++position) {
            this.outputLiteral(this.in[position]);
        }
        this.writeBits(0, 7);
        this.alignToByte();
    }
    
    void store() {
        int start = 0;
        int length = this.inLength;
        int BFINAL = 0;
        while (length > 0) {
            int blockLength;
            if (length < 65535) {
                blockLength = length;
                BFINAL = 1;
            }
            else {
                blockLength = 65535;
                BFINAL = 0;
            }
            this.bytes.write((byte)BFINAL);
            this.writeShortLSB(this.bytes, blockLength);
            this.writeShortLSB(this.bytes, blockLength ^ 0xFFFF);
            this.bytes.write(this.in, start, blockLength);
            length -= blockLength;
            start += blockLength;
        }
    }
    
    public byte[] deflate(final byte[] input) {
        this.in = input;
        this.inLength = input.length;
        this.bytes.write(120);
        this.bytes.write(-100);
        for (int i = 0; i < this.inLength; ++i) {
            this.updateAdler(this.in[i]);
        }
        this.compress();
        this.writeInt(this.bytes, this.adler32);
        return this.bytes.toByteArray();
    }
    
    static {
        mirrorBytes = new short[] { 0, 128, 64, 192, 32, 160, 96, 224, 16, 144, 80, 208, 48, 176, 112, 240, 8, 136, 72, 200, 40, 168, 104, 232, 24, 152, 88, 216, 56, 184, 120, 248, 4, 132, 68, 196, 36, 164, 100, 228, 20, 148, 84, 212, 52, 180, 116, 244, 12, 140, 76, 204, 44, 172, 108, 236, 28, 156, 92, 220, 60, 188, 124, 252, 2, 130, 66, 194, 34, 162, 98, 226, 18, 146, 82, 210, 50, 178, 114, 242, 10, 138, 74, 202, 42, 170, 106, 234, 26, 154, 90, 218, 58, 186, 122, 250, 6, 134, 70, 198, 38, 166, 102, 230, 22, 150, 86, 214, 54, 182, 118, 246, 14, 142, 78, 206, 46, 174, 110, 238, 30, 158, 94, 222, 62, 190, 126, 254, 1, 129, 65, 193, 33, 161, 97, 225, 17, 145, 81, 209, 49, 177, 113, 241, 9, 137, 73, 201, 41, 169, 105, 233, 25, 153, 89, 217, 57, 185, 121, 249, 5, 133, 69, 197, 37, 165, 101, 229, 21, 149, 85, 213, 53, 181, 117, 245, 13, 141, 77, 205, 45, 173, 109, 237, 29, 157, 93, 221, 61, 189, 125, 253, 3, 131, 67, 195, 35, 163, 99, 227, 19, 147, 83, 211, 51, 179, 115, 243, 11, 139, 75, 203, 43, 171, 107, 235, 27, 155, 91, 219, 59, 187, 123, 251, 7, 135, 71, 199, 39, 167, 103, 231, 23, 151, 87, 215, 55, 183, 119, 247, 15, 143, 79, 207, 47, 175, 111, 239, 31, 159, 95, 223, 63, 191, 127, 255 };
        lengthCodes = new Code[] { new Code(257, 0, 3, 3), new Code(258, 0, 4, 4), new Code(259, 0, 5, 5), new Code(260, 0, 6, 6), new Code(261, 0, 7, 7), new Code(262, 0, 8, 8), new Code(263, 0, 9, 9), new Code(264, 0, 10, 10), new Code(265, 1, 11, 12), new Code(266, 1, 13, 14), new Code(267, 1, 15, 16), new Code(268, 1, 17, 18), new Code(269, 2, 19, 22), new Code(270, 2, 23, 26), new Code(271, 2, 27, 30), new Code(272, 2, 31, 34), new Code(273, 3, 35, 42), new Code(274, 3, 43, 50), new Code(275, 3, 51, 58), new Code(276, 3, 59, 66), new Code(277, 4, 67, 82), new Code(278, 4, 83, 98), new Code(279, 4, 99, 114), new Code(280, 4, 115, 130), new Code(281, 5, 131, 162), new Code(282, 5, 163, 194), new Code(283, 5, 195, 226), new Code(284, 5, 227, 257), new Code(285, 0, 258, 258) };
        distanceCodes = new Code[] { new Code(0, 0, 1, 1), new Code(1, 0, 2, 2), new Code(2, 0, 3, 3), new Code(3, 0, 4, 4), new Code(4, 1, 5, 6), new Code(5, 1, 7, 8), new Code(6, 2, 9, 12), new Code(7, 2, 13, 16), new Code(8, 3, 17, 24), new Code(9, 3, 25, 32), new Code(10, 4, 33, 48), new Code(11, 4, 49, 64), new Code(12, 5, 65, 96), new Code(13, 5, 97, 128), new Code(14, 6, 129, 192), new Code(15, 6, 193, 256), new Code(16, 7, 257, 384), new Code(17, 7, 385, 512), new Code(18, 8, 513, 768), new Code(19, 8, 769, 1024), new Code(20, 9, 1025, 1536), new Code(21, 9, 1537, 2048), new Code(22, 10, 2049, 3072), new Code(23, 10, 3073, 4096), new Code(24, 11, 4097, 6144), new Code(25, 11, 6145, 8192), new Code(26, 12, 8193, 12288), new Code(27, 12, 12289, 16384), new Code(28, 13, 16385, 24576), new Code(29, 13, 24577, 32768) };
    }
    
    static class Link
    {
        int hash;
        int value;
        Link previous;
        Link next;
        
        Link() {
            this.hash = 0;
            this.value = 0;
            this.previous = null;
            this.next = null;
        }
    }
    
    static class Match
    {
        int length;
        int distance;
        
        Match(final int length, final int distance) {
            this.length = length;
            this.distance = distance;
        }
    }
    
    static class Code
    {
        int code;
        int extraBits;
        int min;
        int max;
        
        Code(final int code, final int extraBits, final int min, final int max) {
            this.code = code;
            this.extraBits = extraBits;
            this.min = min;
            this.max = max;
        }
    }
}
