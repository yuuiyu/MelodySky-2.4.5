//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.image;

import org.eclipse.swt.graphics.*;
import org.eclipse.swt.*;

public class PngTrnsChunk extends PngChunk
{
    static final int TRANSPARENCY_TYPE_PIXEL = 0;
    static final int TRANSPARENCY_TYPE_ALPHAS = 1;
    static final int RGB_DATA_LENGTH = 6;
    
    PngTrnsChunk(final RGB rgb) {
        super(6);
        this.setType(PngTrnsChunk.TYPE_tRNS);
        this.setInt16(8, rgb.red);
        this.setInt16(10, rgb.green);
        this.setInt16(12, rgb.blue);
        this.setCRC(this.computeCRC());
    }
    
    PngTrnsChunk(final byte[] reference) {
        super(reference);
    }
    
    int getChunkType() {
        return 5;
    }
    
    void validateLength(final PngIhdrChunk header, final PngPlteChunk paletteChunk) {
        boolean valid = false;
        switch (header.getColorType()) {
            case 2: {
                valid = (this.getLength() == 6);
                break;
            }
            case 3: {
                valid = (this.getLength() <= paletteChunk.getLength());
                break;
            }
            case 0: {
                valid = (this.getLength() == 2);
                break;
            }
            default: {
                valid = false;
                break;
            }
        }
        if (!valid) {
            SWT.error(40);
        }
    }
    
    void validate(final PngFileReadState readState, final PngIhdrChunk headerChunk, final PngPlteChunk paletteChunk) {
        if (!readState.readIHDR || (headerChunk.getMustHavePalette() && !readState.readPLTE) || readState.readIDAT || readState.readIEND) {
            SWT.error(40);
        }
        else {
            readState.readTRNS = true;
        }
        this.validateLength(headerChunk, paletteChunk);
        super.validate(readState, headerChunk);
    }
    
    int getTransparencyType(final PngIhdrChunk header) {
        if (header.getColorType() == 3) {
            return 1;
        }
        return 0;
    }
    
    int getSwtTransparentPixel(final PngIhdrChunk header) {
        switch (header.getColorType()) {
            case 0: {
                final int gray = ((this.reference[8] & 0xFF) << 8) + (this.reference[9] & 0xFF);
                if (header.getBitDepth() > 8) {
                    return PNGFileFormat.compress16BitDepthTo8BitDepth(gray);
                }
                return gray & 0xFF;
            }
            case 2: {
                int red = (this.reference[8] & 0xFF) << 8 | (this.reference[9] & 0xFF);
                int green = (this.reference[10] & 0xFF) << 8 | (this.reference[11] & 0xFF);
                int blue = (this.reference[12] & 0xFF) << 8 | (this.reference[13] & 0xFF);
                if (header.getBitDepth() > 8) {
                    red = PNGFileFormat.compress16BitDepthTo8BitDepth(red);
                    green = PNGFileFormat.compress16BitDepthTo8BitDepth(green);
                    blue = PNGFileFormat.compress16BitDepthTo8BitDepth(blue);
                }
                return red << 16 | green << 8 | blue;
            }
            default: {
                SWT.error(40);
                return -1;
            }
        }
    }
    
    byte[] getAlphaValues(final PngIhdrChunk header, final PngPlteChunk paletteChunk) {
        if (header.getColorType() != 3) {
            SWT.error(40);
        }
        final byte[] alphas = new byte[paletteChunk.getPaletteSize()];
        int dataLength;
        int i;
        for (dataLength = this.getLength(), i = 0, i = 0; i < dataLength; ++i) {
            alphas[i] = this.reference[8 + i];
        }
        for (int j = i; j < alphas.length; ++j) {
            alphas[j] = -1;
        }
        return alphas;
    }
}
