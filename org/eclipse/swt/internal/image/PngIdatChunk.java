//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.image;

import org.eclipse.swt.*;

class PngIdatChunk extends PngChunk
{
    static final int HEADER_BYTES_LENGTH = 2;
    static final int ADLER_FIELD_LENGTH = 4;
    static final int HEADER_BYTE1_DATA_OFFSET = 8;
    static final int HEADER_BYTE2_DATA_OFFSET = 9;
    static final int ADLER_DATA_OFFSET = 10;
    
    PngIdatChunk(final byte headerByte1, final byte headerByte2, final byte[] data, final int adler) {
        super(data.length + 2 + 4);
        this.setType(PngIdatChunk.TYPE_IDAT);
        this.reference[8] = headerByte1;
        this.reference[9] = headerByte2;
        System.arraycopy(data, 0, this.reference, 8, data.length);
        this.setInt32(10, adler);
        this.setCRC(this.computeCRC());
    }
    
    PngIdatChunk(final byte[] reference) {
        super(reference);
    }
    
    int getChunkType() {
        return 2;
    }
    
    void validate(final PngFileReadState readState, final PngIhdrChunk headerChunk) {
        if (!readState.readIHDR || (headerChunk.getMustHavePalette() && !readState.readPLTE) || readState.readIEND) {
            SWT.error(40);
        }
        else {
            readState.readIDAT = true;
        }
        super.validate(readState, headerChunk);
    }
    
    byte getDataByteAtOffset(final int offset) {
        return this.reference[8 + offset];
    }
}
