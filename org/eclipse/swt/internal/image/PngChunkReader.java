//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.image;

import org.eclipse.swt.*;

public class PngChunkReader
{
    LEDataInputStream inputStream;
    PngFileReadState readState;
    PngIhdrChunk headerChunk;
    PngPlteChunk paletteChunk;
    
    PngChunkReader(final LEDataInputStream inputStream) {
        this.inputStream = inputStream;
        this.readState = new PngFileReadState();
        this.headerChunk = null;
    }
    
    PngIhdrChunk getIhdrChunk() {
        if (this.headerChunk == null) {
            try {
                final PngChunk chunk = PngChunk.readNextFromStream(this.inputStream);
                if (chunk == null) {
                    SWT.error(40);
                }
                (this.headerChunk = (PngIhdrChunk)chunk).validate(this.readState, null);
            }
            catch (ClassCastException e) {
                SWT.error(40);
            }
        }
        return this.headerChunk;
    }
    
    PngChunk readNextChunk() {
        if (this.headerChunk == null) {
            return this.getIhdrChunk();
        }
        final PngChunk chunk = PngChunk.readNextFromStream(this.inputStream);
        if (chunk == null) {
            SWT.error(40);
        }
        switch (chunk.getChunkType()) {
            case 5: {
                ((PngTrnsChunk)chunk).validate(this.readState, this.headerChunk, this.paletteChunk);
                break;
            }
            case 1: {
                chunk.validate(this.readState, this.headerChunk);
                this.paletteChunk = (PngPlteChunk)chunk;
                break;
            }
            default: {
                chunk.validate(this.readState, this.headerChunk);
                break;
            }
        }
        if (this.readState.readIDAT && chunk.getChunkType() != 2) {
            this.readState.readPixelData = true;
        }
        return chunk;
    }
    
    boolean readPixelData() {
        return this.readState.readPixelData;
    }
    
    boolean hasMoreChunks() {
        return !this.readState.readIEND;
    }
}
