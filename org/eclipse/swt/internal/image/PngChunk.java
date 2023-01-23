//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.image;

import org.eclipse.swt.*;
import java.io.*;

class PngChunk
{
    byte[] reference;
    static final int LENGTH_OFFSET = 0;
    static final int TYPE_OFFSET = 4;
    static final int DATA_OFFSET = 8;
    static final int TYPE_FIELD_LENGTH = 4;
    static final int LENGTH_FIELD_LENGTH = 4;
    static final int MIN_LENGTH = 12;
    static final int CHUNK_UNKNOWN = -1;
    static final int CHUNK_IHDR = 0;
    static final int CHUNK_PLTE = 1;
    static final int CHUNK_IDAT = 2;
    static final int CHUNK_IEND = 3;
    static final int CHUNK_tRNS = 5;
    static final byte[] TYPE_IHDR;
    static final byte[] TYPE_PLTE;
    static final byte[] TYPE_IDAT;
    static final byte[] TYPE_IEND;
    static final byte[] TYPE_tRNS;
    static final int[] CRC_TABLE;
    int length;
    
    PngChunk(final byte[] reference) {
        this.setReference(reference);
        if (reference.length < 4) {
            SWT.error(40);
        }
        this.length = this.getInt32(0);
    }
    
    PngChunk(final int dataLength) {
        this(new byte[12 + dataLength]);
        this.setLength(dataLength);
    }
    
    byte[] getReference() {
        return this.reference;
    }
    
    void setReference(final byte[] reference) {
        this.reference = reference;
    }
    
    int getInt16(final int offset) {
        int answer = 0;
        answer |= (this.reference[offset] & 0xFF) << 8;
        answer |= (this.reference[offset + 1] & 0xFF);
        return answer;
    }
    
    void setInt16(final int offset, final int value) {
        this.reference[offset] = (byte)(value >> 8 & 0xFF);
        this.reference[offset + 1] = (byte)(value & 0xFF);
    }
    
    int getInt32(final int offset) {
        int answer = 0;
        answer |= (this.reference[offset] & 0xFF) << 24;
        answer |= (this.reference[offset + 1] & 0xFF) << 16;
        answer |= (this.reference[offset + 2] & 0xFF) << 8;
        answer |= (this.reference[offset + 3] & 0xFF);
        return answer;
    }
    
    void setInt32(final int offset, final int value) {
        this.reference[offset] = (byte)(value >> 24 & 0xFF);
        this.reference[offset + 1] = (byte)(value >> 16 & 0xFF);
        this.reference[offset + 2] = (byte)(value >> 8 & 0xFF);
        this.reference[offset + 3] = (byte)(value & 0xFF);
    }
    
    int getLength() {
        return this.length;
    }
    
    void setLength(final int value) {
        this.setInt32(0, value);
        this.length = value;
    }
    
    byte[] getTypeBytes() {
        final byte[] type = new byte[4];
        System.arraycopy(this.reference, 4, type, 0, 4);
        return type;
    }
    
    void setType(final byte[] value) {
        if (value.length != 4) {
            SWT.error(5);
        }
        System.arraycopy(value, 0, this.reference, 4, 4);
    }
    
    byte[] getData() {
        final int dataLength = this.getLength();
        if (this.reference.length < 12 + dataLength) {
            SWT.error(6);
        }
        final byte[] data = new byte[dataLength];
        System.arraycopy(this.reference, 8, data, 0, dataLength);
        return data;
    }
    
    void setData(final byte[] data) {
        this.setLength(data.length);
        System.arraycopy(data, 0, this.reference, 8, data.length);
        this.setCRC(this.computeCRC());
    }
    
    int getCRC() {
        final int crcOffset = 8 + this.getLength();
        return this.getInt32(crcOffset);
    }
    
    void setCRC(final int value) {
        final int crcOffset = 8 + this.getLength();
        this.setInt32(crcOffset, value);
    }
    
    int getSize() {
        return 12 + this.getLength();
    }
    
    boolean checkCRC() {
        final int crc = this.computeCRC();
        final int storedCRC = this.getCRC();
        return crc == storedCRC;
    }
    
    int computeCRC() {
        int crc = -1;
        final int start = 4;
        for (int stop = 8 + this.getLength(), i = 4; i < stop; ++i) {
            final int index = (crc ^ this.reference[i]) & 0xFF;
            crc = (PngChunk.CRC_TABLE[index] ^ (crc >> 8 & 0xFFFFFF));
        }
        return ~crc;
    }
    
    boolean typeMatchesArray(final byte[] array) {
        for (int i = 0; i < 4; ++i) {
            if (this.reference[4 + i] != array[i]) {
                return false;
            }
        }
        return true;
    }
    
    boolean isCritical() {
        final char c = (char)this.getTypeBytes()[0];
        return 'A' <= c && c <= 'Z';
    }
    
    int getChunkType() {
        if (this.typeMatchesArray(PngChunk.TYPE_IHDR)) {
            return 0;
        }
        if (this.typeMatchesArray(PngChunk.TYPE_PLTE)) {
            return 1;
        }
        if (this.typeMatchesArray(PngChunk.TYPE_IDAT)) {
            return 2;
        }
        if (this.typeMatchesArray(PngChunk.TYPE_IEND)) {
            return 3;
        }
        if (this.typeMatchesArray(PngChunk.TYPE_tRNS)) {
            return 5;
        }
        return -1;
    }
    
    static PngChunk readNextFromStream(final LEDataInputStream stream) {
        try {
            final int headerLength = 8;
            final byte[] headerBytes = new byte[8];
            int result = stream.read(headerBytes, 0, 8);
            stream.unread(headerBytes);
            if (result != 8) {
                return null;
            }
            final PngChunk tempChunk = new PngChunk(headerBytes);
            final int chunkLength = tempChunk.getSize();
            final byte[] chunk = new byte[chunkLength];
            result = stream.read(chunk, 0, chunkLength);
            if (result != chunkLength) {
                return null;
            }
            switch (tempChunk.getChunkType()) {
                case 0: {
                    return new PngIhdrChunk(chunk);
                }
                case 1: {
                    return new PngPlteChunk(chunk);
                }
                case 2: {
                    return new PngIdatChunk(chunk);
                }
                case 3: {
                    return new PngIendChunk(chunk);
                }
                case 5: {
                    return new PngTrnsChunk(chunk);
                }
                default: {
                    return new PngChunk(chunk);
                }
            }
        }
        catch (IOException e) {
            return null;
        }
    }
    
    void validate(final PngFileReadState readState, final PngIhdrChunk headerChunk) {
        if (this.reference.length < 12) {
            SWT.error(40);
        }
        final byte[] type = this.getTypeBytes();
        char c = (char)type[2];
        if ('A' > c || c > 'Z') {
            SWT.error(40);
        }
        for (int i = 0; i < 4; ++i) {
            c = (char)type[i];
            if (('a' > c || c > 'z') && ('A' > c || c > 'Z')) {
                SWT.error(40);
            }
        }
        if (!this.checkCRC()) {
            SWT.error(40);
        }
    }
    
    void contributeToString(final StringBuilder buffer) {
    }
    
    @Override
    public String toString() {
        final StringBuilder buffer = new StringBuilder();
        buffer.append("{");
        buffer.append("\n\tLength: ");
        buffer.append(this.getLength());
        buffer.append("\n\tType: ");
        for (final byte element : this.getTypeBytes()) {
            buffer.append((char)element);
        }
        this.contributeToString(buffer);
        buffer.append("\n\tCRC: ");
        buffer.append(Integer.toHexString(this.getCRC()));
        buffer.append("\n}");
        return buffer.toString();
    }
    
    static {
        TYPE_IHDR = new byte[] { 73, 72, 68, 82 };
        TYPE_PLTE = new byte[] { 80, 76, 84, 69 };
        TYPE_IDAT = new byte[] { 73, 68, 65, 84 };
        TYPE_IEND = new byte[] { 73, 69, 78, 68 };
        TYPE_tRNS = new byte[] { 116, 82, 78, 83 };
        CRC_TABLE = new int[256];
        for (int i = 0; i < 256; ++i) {
            PngChunk.CRC_TABLE[i] = i;
            for (int j = 0; j < 8; ++j) {
                if ((PngChunk.CRC_TABLE[i] & 0x1) == 0x0) {
                    PngChunk.CRC_TABLE[i] = (PngChunk.CRC_TABLE[i] >> 1 & Integer.MAX_VALUE);
                }
                else {
                    PngChunk.CRC_TABLE[i] = (0xEDB88320 ^ (PngChunk.CRC_TABLE[i] >> 1 & Integer.MAX_VALUE));
                }
            }
        }
    }
}
