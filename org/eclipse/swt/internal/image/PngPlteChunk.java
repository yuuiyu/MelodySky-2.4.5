//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.image;

import org.eclipse.swt.graphics.*;
import org.eclipse.swt.*;

class PngPlteChunk extends PngChunk
{
    int paletteSize;
    
    PngPlteChunk(final PaletteData palette) {
        super(palette.getRGBs().length * 3);
        this.paletteSize = this.length / 3;
        this.setType(PngPlteChunk.TYPE_PLTE);
        this.setPaletteData(palette);
        this.setCRC(this.computeCRC());
    }
    
    PngPlteChunk(final byte[] reference) {
        super(reference);
        this.paletteSize = this.length / 3;
    }
    
    int getChunkType() {
        return 1;
    }
    
    int getPaletteSize() {
        return this.paletteSize;
    }
    
    PaletteData getPaletteData() {
        final RGB[] rgbs = new RGB[this.paletteSize];
        for (int i = 0; i < rgbs.length; ++i) {
            final int offset = 8 + i * 3;
            final int red = this.reference[offset] & 0xFF;
            final int green = this.reference[offset + 1] & 0xFF;
            final int blue = this.reference[offset + 2] & 0xFF;
            rgbs[i] = new RGB(red, green, blue);
        }
        return new PaletteData(rgbs);
    }
    
    void setPaletteData(final PaletteData palette) {
        final RGB[] rgbs = palette.getRGBs();
        for (int i = 0; i < rgbs.length; ++i) {
            final int offset = 8 + i * 3;
            this.reference[offset] = (byte)rgbs[i].red;
            this.reference[offset + 1] = (byte)rgbs[i].green;
            this.reference[offset + 2] = (byte)rgbs[i].blue;
        }
    }
    
    void validate(final PngFileReadState readState, final PngIhdrChunk headerChunk) {
        if (!readState.readIHDR || readState.readPLTE || readState.readTRNS || readState.readIDAT || readState.readIEND) {
            SWT.error(40);
        }
        else {
            readState.readPLTE = true;
        }
        super.validate(readState, headerChunk);
        if (this.getLength() % 3 != 0) {
            SWT.error(40);
        }
        if (1 << headerChunk.getBitDepth() < this.paletteSize) {
            SWT.error(40);
        }
        if (256 < this.paletteSize) {
            SWT.error(40);
        }
    }
    
    void contributeToString(final StringBuilder buffer) {
        buffer.append("\n\tPalette size:");
        buffer.append(this.paletteSize);
    }
}
